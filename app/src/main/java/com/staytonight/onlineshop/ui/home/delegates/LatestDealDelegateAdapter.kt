package com.staytonight.onlineshop.ui.home.delegates

import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.staytonight.domain.model.LatestDealSection
import com.staytonight.onlineshop.R
import com.staytonight.onlineshop.base.utils.delegate_adapter.ExtensionViewHolder
import com.staytonight.onlineshop.base.utils.delegate_adapter.LayoutItemDelegate
import com.staytonight.onlineshop.ui.home.adapters.LastDealAdapter

class LatestDealDelegateAdapter(
    private val onItemClick: () -> Unit
) : LayoutItemDelegate<LatestDealSection>(R.layout.item_section) {

    override fun bindView(
        position: Int,
        item: LatestDealSection,
        holder: ExtensionViewHolder<LatestDealSection>,
        payloads: List<Any>
    ) {
        super.bindView(position, item, holder, payloads)
        with(holder.itemView) {
            val rv = findViewById<RecyclerView>(R.id.rv_section)
            findViewById<TextView>(R.id.tv_title_section).text =
                context.getString(item.titleStringId)
            rv.layoutManager =
                LinearLayoutManager(holder.itemView.context, LinearLayoutManager.HORIZONTAL, false)
            rv.findViewById<RecyclerView>(R.id.rv_section).adapter =
                LastDealAdapter(item.items.toMutableList(), onItemClick)
            findViewById<TextView>(R.id.tv_show_all).setOnClickListener {

            }
        }
    }

    override fun isForViewType(model: Any): Boolean = model is LatestDealSection

    override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean = oldItem == newItem

    override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
        return oldItem is LatestDealSection &&
                newItem is LatestDealSection
    }

}