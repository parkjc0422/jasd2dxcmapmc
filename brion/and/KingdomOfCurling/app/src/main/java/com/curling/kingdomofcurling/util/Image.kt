package com.curling.kingdomofcurling.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Build

fun toBitmap(context: Context, resId:Int) : Bitmap {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        return (context.getDrawable(resId) as BitmapDrawable).bitmap
    } else {
        return  (context.resources.getDrawable(resId) as BitmapDrawable).bitmap
    }
}