package com.staytonight.onlineshop.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.staytonight.onlineshop.R
import com.staytonight.onlineshop.databinding.ItemDetailsImageBinding

class DetailsAdapter(
    private val items: List<String>,
    private val onItemClick: (image: String) -> Unit
) : RecyclerView.Adapter<DetailsAdapter.DetailsImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsImageViewHolder =
        DetailsImageViewHolder(
            ItemDetailsImageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: DetailsImageViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class DetailsImageViewHolder(
        private val item: ItemDetailsImageBinding
    ) : RecyclerView.ViewHolder(item.root) {

        fun bind(image: String) {
            item.apply {
                Glide.with(ivImage.context)
                    .load(image)
                    .error(R.drawable.vector_1)
                    .centerCrop()
                    .into(ivImage)
                ivImage.setOnClickListener {
                    onItemClick.invoke(image)
                }
            }
        }

    }
}