package com.example.playsnapui.ui.`object`

import SharedData
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.flexbox.FlexboxLayoutManager
import com.example.playsnapui.R
import com.example.playsnapui.databinding.FragmentObjectBinding
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.JustifyContent

class ObjectFragment : Fragment() {

    private var _binding: FragmentObjectBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ObjectViewModel by viewModels()

    private lateinit var adapter: ObjectAdapter
    private val detectedObjects = SharedData.detectedObjects

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentObjectBinding.inflate(inflater, container, false)
        println("DetectedObjects in objectFragment: $detectedObjects")
        viewModel.setDetectedObjects(detectedObjects)
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.setOnTouchListener { _, _ ->
            hideKeyboard()
            false
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Ganti dengan tujuan back yang kamu inginkan
                findNavController().navigate(R.id.action_ObjectFragment_to_HomeFragment)
            }
        })

        binding.btnBack.setOnClickListener {
            SharedData.detectedObjects = emptyList()
            findNavController().navigate(R.id.action_ObjectFragment_to_HomeFragment)
        }

        // Set up RecyclerView
        adapter = ObjectAdapter(mutableListOf()) { position ->
            viewModel.removeObjectAt(position)
        }

//        val gridLayoutManager = GridLayoutManager(requireContext(), 2)  // 2 columns in grid
//        binding.recyclerPopobject.layoutManager = gridLayoutManager
//        binding.recyclerPopobject.adapter = adapter

        val flexboxLayoutManager = FlexboxLayoutManager(requireContext())
        flexboxLayoutManager.justifyContent = JustifyContent.FLEX_START // Align items to the left
        flexboxLayoutManager.alignItems = AlignItems.FLEX_START // Start alignment for items
        flexboxLayoutManager.flexWrap = FlexWrap.WRAP // Enable wrapping of items to the next line

        binding.recyclerPopobject.layoutManager = flexboxLayoutManager
        binding.recyclerPopobject.adapter = adapter

        // Observe data from ViewModel
        viewModel.objects.observe(viewLifecycleOwner, Observer { newList ->
            println("New data received in Fragment: $newList")  // Debugging log
            adapter.updateData(newList)
        })

        binding.addButton.setOnClickListener {
            val newObject = binding.etSearchGame.text.toString().trim()
            if (newObject.isNotEmpty()) {
                viewModel.addObject(newObject)  // Add object to ViewModel
                binding.etSearchGame.text?.clear()  // Clear input after adding
            }
        }

        binding.etSearchGame.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN)) {
                binding.addButton.performClick() // Panggil fungsi klik tombol pencarian
                return@setOnEditorActionListener true // Konsumsi event
            }
            false
        }

        binding.mulaiButton.setOnClickListener {
            // Fetch recommended games from ViewModel based on detected objects
            val detectedObjects = viewModel.objects.value.orEmpty()
            SharedData.detectedObjects = detectedObjects
            viewModel.getRecommendedGames(detectedObjects)

            // Observe recommended games from ViewModel
            viewModel.recommendedGames.observe(viewLifecycleOwner, Observer { recommendedGames ->
                // Save the recommended games (Games objects) to SharedData
                SharedData.recommendedGames = recommendedGames
                Log.d("ObjectFragment", "Recommended games size: ${SharedData.recommendedGames.size}")

                SharedData.isObject = true
                // Navigate to the recommendation page
                findNavController().navigate(R.id.action_ObjectFragment_to_RecommendGameFragment)
            })
        }
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
