package com.curling.kingdomofcurling.ui.camera.module

import android.graphics.Bitmap

class WaterMark {
    var bitmap: Bitmap? = null
    var frame:OverlayFrame? = null

    constructor(bitmap: Bitmap, frame: OverlayFrame) {
        this.frame = frame
        this.bitmap =  bitmap
    }
}