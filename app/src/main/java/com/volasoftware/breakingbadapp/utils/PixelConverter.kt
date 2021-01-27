package com.volasoftware.breakingbadapp.utils

import android.content.res.Resources

object PixelConverter {

    fun dpToPx(dp: Int): Int {
        return (dp * Resources.getSystem().displayMetrics.density).toInt()
    }
}