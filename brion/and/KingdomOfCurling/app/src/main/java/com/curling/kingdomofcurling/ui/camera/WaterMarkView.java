package com.curling.kingdomofcurling.ui.camera;

import android.content.Context;
import android.graphics.Canvas;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

import com.curling.kingdomofcurling.ui.camera.module.WaterMark;

public class WaterMarkView extends GLSurfaceView {
    public WaterMarkView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    private WaterMark waterMark;
    public void addWaterMark (WaterMark waterMark) {
        Canvas canvas = getHolder().lockCanvas();
        this.waterMark = waterMark;
        if(canvas != null) {
            draw(canvas);
            getHolder().unlockCanvasAndPost(canvas);
        }
    }
}
