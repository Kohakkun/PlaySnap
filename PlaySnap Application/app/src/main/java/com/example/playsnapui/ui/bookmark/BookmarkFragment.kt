package com.example.playsnapui.ui.bookmark

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.playsnapui.R
import com.example.playsnapui.data.Games
import com.example.playsnapui.databinding.FragmentBookmarkBinding
import com.example.playsnapui.ui.home.HomeAdapterPopular

class BookmarkFragment : Fragment() {

    private lateinit var binding: FragmentBookmarkBinding
    private val bookmarkViewModel: BookmarkViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize RecyclerView
        val adapter = HomeAdapterPopular(arrayListOf()) // Pass an empty list initially
        val layoutManager = GridLayoutManager(requireContext(), 2) // 2 columns in the grid
        binding.recentRecyclerBookmark.layoutManager = layoutManager
        binding.recentRecyclerBookmark.adapter = adapter


        Log.d("Haha", "Cek1")
        bookmarkViewModel.fetchBookmarkedGames()
        Log.d("Haha", "Cek2")

        // Observe the bookmarked games
        bookmarkViewModel.bookmarkedGames.observe(viewLifecycleOwner, Observer { games ->
            games?.let {
                adapter.gameList.clear()
                adapter.gameList.addAll(it)
                adapter.notifyDataSetChanged()
            }
        })


    }
}
