package com.staytonight.onlineshop.base.utils.delegate_adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class ItemDelegate<Model, VH : RecyclerView.ViewHolder> {
    abstract fun isForViewType(model: Any): Boolean
    abstract fun createViewHolder(parent: ViewGroup): VH
    abstract fun bindView(position: Int, item: Model, holder: VH, payloads: List<Any>)
    abstract fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean
    abstract fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean
    open fun onViewRecycled(holder: VH) = Unit
    open fun getChangePayload(oldItem: Any, newItem: Any): Any? = null
}