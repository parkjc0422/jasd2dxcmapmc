package com.curling.kingdomofcurling.ui

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.View
import android.widget.Toast
import com.curling.kingdomofcurling.R
import com.curling.kingdomofcurling.ui.camera.CameraViewActivity
import com.curling.kingdomofcurling.ui.fragment.BookingFragment
import com.curling.kingdomofcurling.ui.fragment.MainFragment
import com.curling.kingdomofcurling.ui.fragment.TravelInfoFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_travel_info.*
import android.provider.SyncStateContract.Helpers.update
import android.content.pm.PackageManager
import android.content.pm.PackageInfo
import android.util.Base64
import android.util.Log
import com.curling.kingdomofcurling.ui.fragment.CouponFragment
import java.security.MessageDigest


interface MainActivityTitleListener {
    fun pressedRightButton()
    fun pressedLeftButton()
}

class MainActivity : FragmentActivity() {
    var listener: MainActivityTitleListener? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initTab()
        setCurrentPage(MainFragment.instance)

        /**
         * for Regist Kakao Map SDK Key Hash
         */
        val info = packageManager.getPackageInfo("com.curling.kingdomofcurling", PackageManager.GET_SIGNATURES)
        for (signature in info.signatures) {
            val md = MessageDigest.getInstance("SHA")
            md.update(signature.toByteArray())
            Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT))
        }


    }

    enum class TitleType {
        Title, Image
    }

    private var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }

        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()

        Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }

    fun initTab() {
        navigation_1.setOnClickListener {
            selectTab(0)
            setCurrentPage(MainFragment.instance)
        }

        navigation_2.setOnClickListener {
            selectTab(1)
            setCurrentPage(BookingFragment.newInstance())
        }

        navigation_3.setOnClickListener {
            startActivity(Intent(this, CameraViewActivity::class.java))
        }

        navigation_4.setOnClickListener {
            selectTab(3)
            setCurrentPage(TravelInfoFragment.instance)
        }

        navigation_5.setOnClickListener {
            selectTab(4)
            setCurrentPage(CouponFragment.instance)
        }

        main_title_left_button.setOnClickListener { listener?.pressedLeftButton() }
        main_title_right_button.setOnClickListener { listener?.pressedRightButton() }

        selectTab(0)
    }

    public fun setCurrentPage (frament:Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_fragment, frament)
        transaction.commit()
    }

    public fun makeTitleInfo (type: TitleType) {
        when (type) {
            TitleType.Title-> {
                title_main_image.visibility = View.GONE
                title_main_textview.visibility = View.VISIBLE
                main_title_left_button.visibility = View.VISIBLE
                main_top_layout.setBackgroundResource(R.color.top_title)
            }
            TitleType.Image -> {
                title_main_image.visibility = View.VISIBLE
                title_main_textview.visibility = View.GONE
                main_top_layout.setBackgroundResource(R.color.white)
                main_title_left_button.visibility = View.INVISIBLE
            }
        }
        if(type == TitleType.Image) {

        } else {
        }
    }

    public fun setMainTitle (title:String) {
        title_main_textview.text = title
    }

    fun selectTab (index:Int) {
        var btns = arrayOf(navi_title_1,navi_title_2,navi_title_3,navi_title_4,navi_title_5)
        var imgs = arrayOf(navi_img_1,navi_img_2,navi_img_3,navi_img_4,navi_img_5)
        var imgF = arrayOf(R.drawable.btn_bottom_home_f, R.drawable.btn_bottom_culring_f, R.drawable.btn_bottom_camera_f
                , R.drawable.btn_bottom_info_f, R.drawable.btn_bottom_coupon_f)
        var imgN = arrayOf(R.drawable.btn_bottom_home_n, R.drawable.btn_bottom_culring_n, R.drawable.btn_bottom_camera_n
                , R.drawable.btn_bottom_info_n, R.drawable.btn_bottom_coupon_n)


        (0..4).forEach { i ->

            if( i == index) {
                btns[i].let {
                    it.setTextColor(resources.getColor(R.color.navi_select_color))
                    it.setTypeface(it.typeface, Typeface.BOLD)
                }
                imgs[i].let {
                    it.setImageResource(imgF[i])
                }
            } else {
                btns[i].let {
                    it.setTextColor(resources.getColor(R.color.navi_nor_color))
                    it.setTypeface(it.typeface, Typeface.NORMAL)
                }
                imgs[i].let {
                    it.setImageResource(imgN[i])
                }
            }
        }

    }
}
