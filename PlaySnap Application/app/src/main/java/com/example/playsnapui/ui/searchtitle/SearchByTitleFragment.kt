package com.example.playsnapui.ui.searchtitle

import SharedData
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.playsnapui.data.Games
import com.example.playsnapui.databinding.FragmentSearchByTitleBinding
import com.example.playsnapui.ui.home.HomeAdapterForYou
import com.google.firebase.firestore.FirebaseFirestore
import android.content.Context
import android.util.Log
import android.view.ViewTreeObserver
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.RecyclerView


class SearchByTitleFragment : Fragment() {

    private var _binding: FragmentSearchByTitleBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: SearchByTitleViewModel

    private lateinit var adapter: HomeAdapterForYou
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchByTitleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getAllGames()
        setRecyclerViewHeightBasedOnItems(binding.listGamesSearchtitle)

        binding.etSearchGame.requestFocus()
        val inputMethodManager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(binding.etSearchGame, InputMethodManager.SHOW_IMPLICIT)

        view.setOnTouchListener { _, _ ->
            hideKeyboard()
            false
        }

        val emptyList = ArrayList<Games>()
        val recyclerView = binding.listGamesSearchtitle
        adapter = HomeAdapterForYou(emptyList, childFragmentManager)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this).get(SearchByTitleViewModel::class.java)
        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.etSearchGame.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN)) {

                binding.addButton.performClick() // Jalankan pencarian

                // Sembunyikan keyboard setelah Enter ditekan
                val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(binding.etSearchGame.windowToken, 0)

                return@setOnEditorActionListener true // Konsumsi event
            }
            false
        }


        binding.addButton.setOnClickListener {
            val queryText = binding.etSearchGame.text.toString().trim().lowercase() // Konversi ke lowercase

            if (queryText.isNotEmpty()) {
                val db = FirebaseFirestore.getInstance()

                db.collection("games")
                    .get()
                    .addOnSuccessListener { documents ->
                        val gameList = mutableListOf<Games>()
                        for (document in documents) {
                            val game = document.toObject(Games::class.java)

                            // Periksa apakah queryText ada dalam namaPermainan (case-insensitive)
                            if (game.namaPermainan.lowercase().contains(queryText)) {
                                gameList.add(game)
                            }
                            else if (game.namaPermainan.contains(queryText)) {
                                gameList.add(game)
                            }
                        }
                        adapter.updateGames(gameList) // Perbarui RecyclerView
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
            } else {
                getAllGames() // Panggil kembali semua data jika input kosong
            }
        }

    }

    fun getAllGames(){
        val db = FirebaseFirestore.getInstance()

        db.collection("games")
            .get()
            .addOnSuccessListener { documents ->
                val gameList = mutableListOf<Games>()
                for (document in documents) {
                    val game = document.toObject(Games::class.java)
                    gameList.add(game)
                }
                SharedData.totalGamesCount = gameList.size
                Log.d("Shared Data total count", "${gameList.size}")
                adapter.updateGames(gameList) // Perbarui RecyclerView
            }
            .addOnFailureListener { e ->
                Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }

    }

    private fun setRecyclerViewHeightBasedOnItems(recyclerView: RecyclerView) {
        val recyclerView = binding.listGamesSearchtitle

        recyclerView.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                recyclerView.viewTreeObserver.removeOnGlobalLayoutListener(this)

                // Dapatkan tinggi total dari parent layout
                val parentHeight = binding.root.height

                // Dapatkan posisi Y dari search bar (EditText)
                val searchBarBottom = binding.etSearchGame.bottom

                // Hitung tinggi RecyclerView agar tidak melebihi layar
                val newHeight = parentHeight - searchBarBottom - 20 // Beri margin kecil

                // Set tinggi RecyclerView
                recyclerView.layoutParams.height = newHeight - 50
                recyclerView.requestLayout()
            }
        })
    }

    fun hideKeyboard() {
        val inputMethodManager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val view = requireActivity().currentFocus
        view?.let {
            inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}