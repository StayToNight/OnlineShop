package com.staytonight.onlineshop.base.utils.delegate_adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ExtensionViewHolder<I>(
    override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {
    var holdItem: I? = null
}