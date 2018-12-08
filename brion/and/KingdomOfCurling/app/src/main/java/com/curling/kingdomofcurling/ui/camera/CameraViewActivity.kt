package com.curling.kingdomofcurling.ui.camera

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.Window;
import android.view.WindowManager;
import com.curling.kingdomofcurling.R
import com.curling.kingdomofcurling.ui.camera.module.OverlayFrame
import com.curling.kingdomofcurling.ui.camera.module.WaterMark
import com.curling.kingdomofcurling.util.toBitmap
import kotlinx.android.synthetic.main.activity_camera_view.*


class CameraViewActivity : AppCompatActivity() {
    private var surfaceView: CameraPreview? = null
    private var holder: SurfaceHolder? = null
    private var mCamera: Camera? = null
    private val RESULT_PERMISSIONS = 100
    companion object {
        lateinit var getInstance: CameraViewActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 카메라 프리뷰를  전체화면으로 보여주기 위해 셋팅한다.
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)


        // 안드로이드 6.0 이상 버전에서는 CAMERA 권한 허가를 요청한다.
        requestPermissionCamera()


        cameraBack.setOnClickListener { finish() }
    }

    fun getCamera(): Camera? {
        return mCamera
    }

    private fun setInit() {
        getInstance = this

        // 카메라 객체를 R.layout.activity_main의 레이아웃에 선언한 SurfaceView에서 먼저 정의해야 함으로 setContentView 보다 먼저 정의한다.
        mCamera = Camera.open()

        setContentView(R.layout.activity_camera_view)

        // SurfaceView를 상속받은 레이아웃을 정의한다.
        surfaceView = preview


        // SurfaceView 정의 - holder와 Callback을 정의한다.
        holder = surfaceView!!.getHolder()
        holder!!.addCallback(surfaceView)
        holder!!.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS)


        takePicture.setOnClickListener {
            surfaceView?.takePicture()
        }

        surfaceView?.listener = object :CameraViewInterface {
            override fun onPreviewReady() {
                var waterMark = WaterMark(toBitmap(this@CameraViewActivity, R.drawable.ic_list_map), OverlayFrame(10, 10, 10, 10))
                surfaceView?.addWaterMark(waterMark)
            }

            override fun onPreviewFail() {
            }
        }


    }


    /**
     * @brief android permission check
     */
    fun requestPermissionCamera(): Boolean {
        val sdkVersion = Build.VERSION.SDK_INT
        if (sdkVersion >= Build.VERSION_CODES.M) {

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
            || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
            || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this@CameraViewActivity,
                        arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE),
                        RESULT_PERMISSIONS)
            } else {
                setInit()
            }
        } else {  // version 6 이하일때
            setInit()
            return true
        }
        return true
    }

    /**
     * @brief Android
     */
    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {

        if (RESULT_PERMISSIONS == requestCode) {

            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 권한 허가시
                setInit()
            } else {
                // 권한 거부시
            }
            return
        }

    }
}
