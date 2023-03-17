package com.staytonight.onlineshop.base.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun View?.show() {
    if (this == null) return
    visibility = View.VISIBLE
}

fun View?.hide() {
    if (this == null) return
    visibility = View.GONE
}

fun ViewGroup.inflate(layoutRes: Int): View =
    LayoutInflater.from(context).inflate(layoutRes, this, false)