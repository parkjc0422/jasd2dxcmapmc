package com.curling.kingdomofcurling.ui.coupon

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import com.curling.kingdomofcurling.R
import com.curling.kingdomofcurling.ui.map.CurlingMapActivity
import kotlinx.android.synthetic.main.activity_coupon_detail.*

class CouponDetailActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coupon_detail)

        initAction()
    }
    fun initAction (){
        coupon_buy_back.setOnClickListener { finish() }

        coupon_detail_location.setOnClickListener {
            var mapIntent = Intent(this, CurlingMapActivity::class.java)
            mapIntent.putExtra(CurlingMapActivity.NAME, "스타벅스")

            startActivity(mapIntent)
        }
    }
}
