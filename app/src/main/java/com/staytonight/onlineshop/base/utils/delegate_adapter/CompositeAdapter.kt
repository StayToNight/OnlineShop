package com.staytonight.onlineshop.base.utils.delegate_adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class CompositeAdapter<I : Any>(
    vararg delegates: ItemDelegate<out I, *>
) : ListAdapter<I, RecyclerView.ViewHolder>(DiffUtilCallbackDelegate(*delegates)) {

    private val delegatesList: List<ItemDelegate<I, RecyclerView.ViewHolder>> =
        delegates.map {
            @Suppress("UNCHECKED_CAST")
            it as? ItemDelegate<I, RecyclerView.ViewHolder>
                ?: throw IllegalArgumentException()
        }

    override fun getItemViewType(position: Int) =
        delegatesList.indexOfFirst { it.isForViewType(getItem(position)) }.apply {
            if (this == RecyclerView.INVALID_TYPE) {
                throw IllegalArgumentException("ItemDelegate for viewType ${getItem(position)} not found")
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        delegatesList[viewType].createViewHolder(parent)

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        delegatesList[getItemViewType(position)].bindView(
            position,
            getItem(position),
            holder,
            emptyList()
        )
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: List<Any>
    ) {
        delegatesList[getItemViewType(position)].bindView(
            position,
            getItem(position),
            holder,
            payloads
        )
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        super.onViewRecycled(holder)
        delegatesList[holder.itemViewType].onViewRecycled(holder)
    }
}