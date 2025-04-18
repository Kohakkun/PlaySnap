package com.example.playsnapui.ui.home

import SharedData
import SharedData.deepLinkid
import SharedData.gameDetails
import SharedData.userProfile
import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.example.playsnapui.R
import com.example.playsnapui.data.Games
import com.example.playsnapui.databinding.FragmentHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerViewPopular: RecyclerView
    private lateinit var recyclerViewForYou: RecyclerView
    private lateinit var homeAdapterPopular: HomeAdapterPopular
    private lateinit var homeAdapterForYou: HomeAdapterForYou
    private lateinit var db: FirebaseFirestore
    private lateinit var gamesListPopular: ArrayList<Games>
    private lateinit var gamesListForYou: ArrayList<Games>
    private lateinit var viewModel: HomeViewModel
    private lateinit var tempListPopular : ArrayList<Games>
    private lateinit var tempListForYou : ArrayList<Games>
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        userProfile?.let { binding.tvTitleName.text = it.username ?: "N/A" }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout)

        swipeRefreshLayout.setOnRefreshListener {
            refreshData()
        }

        Log.d("From Home Fragment", "$deepLinkid ????")

        requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)?.visibility = View.VISIBLE

        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        db = FirebaseFirestore.getInstance()
        gamesListPopular = arrayListOf()
        gamesListForYou = arrayListOf()

        setupRecyclerViews()
        setupListeners()

        Log.d("Check deeplink: ", "$deepLinkid")
        if (deepLinkid != "") {
            FirebaseFirestore.getInstance().collection("games")
                .document(deepLinkid!!)
                .get()
                .addOnSuccessListener { document: DocumentSnapshot ->
                    if (document.exists()) {
                        gameDetails = document.toObject(Games::class.java)
                        if (gameDetails != null) {
                            deepLinkid = ""
                            findNavController().navigate(R.id.action_PopularFragment_to_TutorialFragment)
                        }
                    }
                }
                .addOnFailureListener { e: Exception? ->
                    Log.e(
                        "Firestore",
                        "Error fetching game details",
                        e
                    )
                }
        }


    }

    private fun setupRecyclerViews() {
        recyclerViewPopular = binding.recentRecyclerPopgame.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = HomeAdapterPopular(gamesListPopular,childFragmentManager).also { homeAdapterPopular = it }
            visibility = View.VISIBLE
        }

        recyclerViewForYou = binding.recentRecyclerForyou.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            isNestedScrollingEnabled = true
            adapter = HomeAdapterForYou(gamesListForYou, childFragmentManager).also { homeAdapterForYou = it }
            visibility = View.VISIBLE
        }
    }

    private fun setupListeners() {
        binding.apply {
            btnFilterGame.setOnClickListener { findNavController().navigate(R.id.action_homeFragment_to_filterFragment) }
            btnScanObject.setOnClickListener { findNavController().navigate(R.id.action_HomeFragment_to_SnapFragment) }
            btnTypeObject.setOnClickListener {
                SharedData.detectedObjects = emptyList()
                findNavController().navigate(R.id.action_HomeFragment_to_ObjectFragment)
            }
            helpButton.setOnClickListener { findNavController().navigate(R.id.action_homeFragment_to_HelpFragment) }
            etSearchGame.setOnClickListener { findNavController().navigate(R.id.action_HomeFragment_to_SearchByTitleFragment) }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) { requireActivity().finish() }
    }

    private fun refreshData() {
        // Tambahkan logika refresh, misalnya memuat ulang data dari server atau database lokal
        loadGamesFromFirestore()
        Toast.makeText(requireContext(), "Refreshing...", Toast.LENGTH_SHORT).show()
        Handler(Looper.getMainLooper()).postDelayed({
            swipeRefreshLayout.isRefreshing = false // Hentikan animasi refresh
        }, 1000) // Simulasi delay 2 detik
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun loadGamesFromFirestore() {
        val batasGames = 10

        db.collection("games").get()
            .addOnSuccessListener { value ->
                val tempListPopular = mutableListOf<Games>()
                val tempListForYou = mutableListOf<Games>()

                value.documents.forEach { document ->
                    document.toObject(Games::class.java)?.let {
                        tempListPopular.add(it)
                        tempListForYou.add(it)
                    }
                }

                gamesListForYou.apply {
                    clear()
                    addAll(tempListForYou.shuffled().take(batasGames))
                }
                gamesListPopular.apply {
                    clear()
                    addAll(tempListPopular.sortedByDescending { it.rating }.take(batasGames))
                }

                if (isAdded) {
                    requireActivity().runOnUiThread {
                        homeAdapterPopular.notifyDataSetChanged()
                        homeAdapterForYou.notifyDataSetChanged()
                        setRecyclerViewHeightBasedOnItems(binding.recentRecyclerForyou)
                    }
                }
            }
            .addOnFailureListener { Log.e("Firestore Error", it.message.toString()) }
    }

    private fun setRecyclerViewHeightBasedOnItems(recyclerView: RecyclerView) {
        recyclerView.post {
            val adapter = recyclerView.adapter ?: return@post
            val layoutManager = recyclerView.layoutManager as? LinearLayoutManager ?: return@post

            if (adapter.itemCount > 0) {
                recyclerView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
                val totalHeight = recyclerView.measuredHeight
                recyclerView.layoutParams = recyclerView.layoutParams.apply { height = totalHeight }
            }

            binding.swipeRefreshLayout.isRefreshing = false // Berhenti refresh setelah data dimuat

        }
    }

    override fun onResume() {
        super.onResume()
        userProfile?.let { binding.tvTitleName.text = it.username ?: "N/A" }
        loadGamesFromFirestore()
    }



}
