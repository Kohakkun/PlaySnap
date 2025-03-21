package com.example.playsnapui.ui.gallery.swipe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.playsnapui.R

class SwipeGalleryAdapter(
    private var images: MutableList<String>, // ✅ FIX: MutableList to allow modifications
    private val onItemChecked: (Int, Boolean) -> Unit
) : RecyclerView.Adapter<SwipeGalleryAdapter.GalleryViewHolder>() {

    val selectedItems = mutableSetOf<Int>() // Track checked items

    fun submitList(newImages: List<String>) {
        images.clear() // ✅ Fix: Now images is mutable
        images.addAll(newImages)
        selectedItems.clear() // Reset checked state
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_taken_photo_bigger, parent, false)
        return GalleryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        holder.bind(images[position], position)
    }

    override fun getItemCount(): Int = images.size

    inner class GalleryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)
        private val checkButton: Button = itemView.findViewById(R.id.btn_check)

        fun bind(imageUrl: String, position: Int) {
            // Load image using Glide with placeholder & error image
            Glide.with(itemView.context)
                .load(imageUrl)
                .into(imageView)

            updateCheckButton(position)

            checkButton.setOnClickListener {
                checkButton.setBackgroundResource(R.drawable.btn_check_pressed_taken_photo)
                if (selectedItems.contains(position)) {
                    selectedItems.remove(position)
                } else {
                    selectedItems.add(position)
                }
                onItemChecked(position, selectedItems.contains(position))
                updateCheckButton(position)
            }
        }


        private fun updateCheckButton(position: Int) {
            if(selectedItems.contains(position)) checkButton.setBackgroundResource(R.drawable.btn_check_pressed_taken_photo) else checkButton.setBackgroundResource(R.drawable.btn_check_not_pressed_taken_photo)
            checkButton.setCompoundDrawablesWithIntrinsicBounds(
                0,
                if (selectedItems.contains(position)) R.drawable.baseline_check_24 else 0,
                0,
                0
            )
        }
    }

    // Function to remove selected images
    fun removeCheckedImages() {
        val uncheckedImages = images.filterIndexed { index, _ -> index !in selectedItems }
        images.clear()
        images.addAll(uncheckedImages)
        selectedItems.clear()
        notifyDataSetChanged()
    }

    fun setChecked(position: Int, isChecked: Boolean) {
        if (isChecked) {
            selectedItems.add(position)
        } else {
            selectedItems.remove(position)
        }
        notifyItemChanged(position)
    }

    fun getCurrentImagePaths(): List<String> {
        return images
    }
    fun updateImages(newImages: List<String>) {
        images = newImages.toMutableList()
        notifyDataSetChanged() // Refresh UI
    }


}
