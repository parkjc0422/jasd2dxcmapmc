package com.curling.kingdomofcurling.ui.join

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import com.curling.kingdomofcurling.R
import kotlinx.android.synthetic.main.activity_join.*

class JoinActivity : FragmentActivity() {
    var listener : JoinListener? = null
    var joinInfo = JoinInfo()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)


        setCurrentPage(JoinProvisionFragment.instance)
        login_join_title_left_button.setOnClickListener { listener?.didBackPress() }
    }


    fun setCurrentPage(frament:Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.joinEmailFrame, frament)
        transaction.commit()
    }


    fun setTitleText(title:String){
        title_join_textview.text = title
    }


    interface JoinListener {
        fun didBackPress()
    }
}
