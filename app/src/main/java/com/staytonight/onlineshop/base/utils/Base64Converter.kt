package com.staytonight.onlineshop.base.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import java.io.ByteArrayOutputStream


class Base64Converter {
    companion object {
        fun convertToBase64(bitmap: Bitmap): String {
            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 10, baos)
            val b: ByteArray = baos.toByteArray()

            return Base64.encodeToString(b, Base64.DEFAULT)
        }

        fun convertToBitmap(image: String): Bitmap {
            val b: ByteArray = Base64.decode(image, Base64.DEFAULT)
            return BitmapFactory.decodeByteArray(b, 0, b.size)
        }
    }
}