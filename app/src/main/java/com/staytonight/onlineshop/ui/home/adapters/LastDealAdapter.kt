package com.staytonight.onlineshop.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.staytonight.domain.model.Latest
import com.staytonight.onlineshop.R
import com.staytonight.onlineshop.databinding.ItemLastDealBinding

class LastDealAdapter(
    private val items: MutableList<Latest>,
    private val onItemClick: () -> Unit
) : RecyclerView.Adapter<LastDealAdapter.LastDealViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LastDealViewHolder =
        LastDealViewHolder(
            ItemLastDealBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: LastDealViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class LastDealViewHolder(
        private val item: ItemLastDealBinding
    ) : RecyclerView.ViewHolder(item.root) {

        fun bind(latest: Latest) {
            item.apply {
                tvCategory.text = latest.category
                tvTitle.text = latest.name
                tvPrice.text = String.format(
                    tvCategory.context.getString(R.string.balance_placeholder),
                    latest.price.toString()
                )
                Glide.with(tvCategory.context)
                    .load(latest.imageUrl)
                    .error(R.drawable.vector_1)
                    .centerCrop()
                    .into(ivImage)
                item.root.setOnClickListener {
                    onItemClick.invoke()
                }
            }
        }

    }
}