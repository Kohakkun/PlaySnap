package com.example.playsnapui.ui.gallery.scroll

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.playsnapui.R

class ScrollGalleryAdapter(
    private val imagePaths: MutableList<String>,
    private val onItemChecked: (Int, Boolean) -> Unit
) : RecyclerView.Adapter<ScrollGalleryAdapter.GalleryViewHolder>() {

    private val selectedItems = mutableSetOf<Int>()

    // Add this method to restore checked state
    fun setSelectedItems(selectedItems: Set<Int>) {
        this.selectedItems.clear()
        this.selectedItems.addAll(selectedItems)
        notifyDataSetChanged()
    }

    inner class GalleryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageView)
        val btnCheck: AppCompatButton = view.findViewById(R.id.btn_check_small)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_taken_photo, parent, false)
        return GalleryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        val imagePath = imagePaths[position]

        // Load image using Glide
        Glide.with(holder.itemView.context).load(imagePath).centerCrop().into(holder.imageView)

        // Update button state based on selection
        val isSelected = selectedItems.contains(position)

        holder.btnCheck.apply {
            if (isSelected) {
                setBackgroundResource(R.drawable.btn_check_pressed_taken_photo) // Default background
                setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.baseline_check_24, 0, 0) // Show drawableTop
            } else {
                setBackgroundResource(R.drawable.btn_check_not_pressed_taken_photo) // Default background
                setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0) // Remove drawableTop
            }
        }

        // Handle click event for checking/unchecking
        holder.btnCheck.setOnClickListener {
            if (selectedItems.contains(position)) {
                selectedItems.remove(position) // Uncheck
            } else {
                selectedItems.add(position) // Check
            }
            notifyItemChanged(position) // Update UI
            onItemChecked(position, selectedItems.contains(position))
        }
    }

    override fun getItemCount(): Int = imagePaths.size
}