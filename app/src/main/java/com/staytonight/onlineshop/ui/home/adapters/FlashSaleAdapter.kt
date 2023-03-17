package com.staytonight.onlineshop.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.staytonight.domain.model.FlashSale
import com.staytonight.onlineshop.R
import com.staytonight.onlineshop.databinding.ItemFlashSaleBinding

class FlashSaleAdapter(
    private val items: MutableList<FlashSale>,
    private val onItemClick: () -> Unit
) : RecyclerView.Adapter<FlashSaleAdapter.LastDealViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LastDealViewHolder =
        LastDealViewHolder(
            ItemFlashSaleBinding.inflate(
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
        private val item: ItemFlashSaleBinding
    ) : RecyclerView.ViewHolder(item.root) {

        fun bind(sale: FlashSale) {
            item.apply {
                tvCategory.text = sale.category
                tvTitle.text = sale.name
                tvPrice.text = String.format(
                    tvCategory.context.getString(R.string.balance_placeholder),
                    sale.price.toString()
                )
                tvDiscount.text = String.format(
                    tvCategory.context.getString(R.string.discount_holder),
                    sale.discount
                )
                Glide.with(tvCategory.context)
                    .load(sale.imageUrl)
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