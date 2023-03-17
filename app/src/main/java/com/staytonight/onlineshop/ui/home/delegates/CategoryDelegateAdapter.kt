package com.staytonight.onlineshop.ui.home.delegates

import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.staytonight.domain.model.CategorySection
import com.staytonight.onlineshop.R
import com.staytonight.onlineshop.base.extensions.hide
import com.staytonight.onlineshop.base.utils.delegate_adapter.ExtensionViewHolder
import com.staytonight.onlineshop.base.utils.delegate_adapter.LayoutItemDelegate
import com.staytonight.onlineshop.ui.home.adapters.CategoryAdapter

class CategoryDelegateAdapter : LayoutItemDelegate<CategorySection>(R.layout.item_section) {

    override fun bindView(
        position: Int,
        item: CategorySection,
        holder: ExtensionViewHolder<CategorySection>,
        payloads: List<Any>
    ) {
        super.bindView(position, item, holder, payloads)
        with(holder.itemView) {
            val rv = findViewById<RecyclerView>(R.id.rv_section)
            findViewById<TextView>(R.id.tv_title_section).hide()
            findViewById<TextView>(R.id.tv_show_all).hide()
            rv.layoutManager =
                LinearLayoutManager(holder.itemView.context, LinearLayoutManager.HORIZONTAL, false)
            rv.findViewById<RecyclerView>(R.id.rv_section).adapter =
                CategoryAdapter(item.items.toMutableList())
            findViewById<TextView>(R.id.tv_show_all).setOnClickListener {

            }
        }
    }

    override fun isForViewType(model: Any): Boolean = model is CategorySection

    override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean = oldItem == newItem

    override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
        return oldItem is CategorySection &&
                newItem is CategorySection
    }

}