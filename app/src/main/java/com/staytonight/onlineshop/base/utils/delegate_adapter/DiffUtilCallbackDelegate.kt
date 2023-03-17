package com.staytonight.onlineshop.base.utils.delegate_adapter

import androidx.recyclerview.widget.DiffUtil

class DiffUtilCallbackDelegate<T : Any>(
    private vararg val delegates: ItemDelegate<out T, *>
) : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        val delegate = delegates.find { itemDelegate ->
            itemDelegate.isForViewType(oldItem) && itemDelegate.isForViewType(newItem)
        } ?: return false

        return delegate.areItemsTheSame(oldItem, newItem)
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        val delegate = delegates.find { itemDelegate ->
            itemDelegate.isForViewType(oldItem) && itemDelegate.isForViewType(newItem)
        } ?: return false

        return delegate.areContentsTheSame(oldItem, newItem)
    }

    override fun getChangePayload(oldItem: T, newItem: T): Any? =
        delegates.find { itemDelegate ->
            itemDelegate.isForViewType(oldItem) && itemDelegate.isForViewType(newItem)
        }?.getChangePayload(oldItem, newItem)
}