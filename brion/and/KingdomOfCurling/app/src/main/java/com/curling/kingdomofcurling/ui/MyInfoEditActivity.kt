package com.curling.kingdomofcurling.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.FragmentActivity
import android.support.v4.view.ViewPager
import com.curling.kingdomofcurling.R
import com.curling.kingdomofcurling.ui.fragment.myinfo.adapter.MyInfoTabAdapter
import kotlinx.android.synthetic.main.activity_my_info_edit.*

class MyInfoEditActivity : FragmentActivity() {
    var listener: ()->Unit = {}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_info_edit)

        initComponent()
    }

    fun initComponent () {
        setActivityTitle("회원정보 변경")
        val adapter = MyInfoTabAdapter(supportFragmentManager)
        myInfoView.adapter = adapter

        my_info_edit_tab.setupWithViewPager(myInfoView)

//
//        my_info_edit_tab.addTab(my_info_edit_tab.newTab().setText("전화번호 변경"))
//        my_info_edit_tab.addTab(my_info_edit_tab.newTab().setText("비밀번호 변경"))
        my_info_edit_tab.let {
            it.tabGravity = TabLayout.GRAVITY_FILL
            it.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabReselected(tab: TabLayout.Tab?) {

                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                override fun onTabSelected(tab: TabLayout.Tab?) {
                    myInfoView.setCurrentItem(tab?.position as Int, true)
                }
            })
        }

    }

    fun setActivityTitle(title:String) {
        my_info_edit_title.text = title
    }


}
