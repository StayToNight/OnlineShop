package com.staytonight.onlineshop.base.extensions

import android.content.Context
import android.util.TypedValue

fun Int.toDp(context: Context?): Int{
    if (context == null) return 0
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        context.resources.displayMetrics
    ).toInt()
}