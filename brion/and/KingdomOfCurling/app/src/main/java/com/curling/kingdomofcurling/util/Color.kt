package com.curling.kingdomofcurling.util

import android.content.Context
import android.os.Build

fun getColor(context: Context, id:Int) :Int {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        return context.getColor(id)
    } else {
        return context.resources.getColor(id)
    }
}