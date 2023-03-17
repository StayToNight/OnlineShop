package com.staytonight.onlineshop.base.utils.delegate_adapter

import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import com.staytonight.onlineshop.base.extensions.inflate

typealias ExtensionItemDelegate<T> = ItemDelegate<T, ExtensionViewHolder<T>>

abstract class LayoutItemDelegate<I>(
    @LayoutRes private val layoutId: Int
) : ExtensionItemDelegate<I>() {

    override fun createViewHolder(parent: ViewGroup) =
        ExtensionViewHolder<I>(parent.inflate(layoutId))

    @CallSuper
    override fun bindView(
        position: Int,
        item: I,
        holder: ExtensionViewHolder<I>,
        payloads: List<Any>
    ) {
        holder.holdItem = item
    }
}