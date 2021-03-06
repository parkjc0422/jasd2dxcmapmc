package com.curling.kingdomofcurling.ui.fragment


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.curling.kingdomofcurling.R
import com.curling.kingdomofcurling.ui.MainActivity
import com.curling.kingdomofcurling.ui.MainActivityTitleListener
import com.curling.kingdomofcurling.ui.coupon.CouponDetailActivity
import com.curling.kingdomofcurling.ui.fragment.myinfo.MyInfoFragment
import kotlinx.android.synthetic.main.fragment_coupon.*

/**
 * A simple [Fragment] subclass.
 *
 */
class CouponFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_coupon, container, false)
    }
    lateinit var parent: MainActivity
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        parent = activity as MainActivity
        parent.setMainTitle(resources.getString(R.string.coupon_menu_title))
        parent.makeTitleInfo(MainActivity.TitleType.Title)
        parent.listener = object :MainActivityTitleListener {
            override fun pressedRightButton() {
                parent.setCurrentPage(MyInfoFragment.getInstance(instance))
            }

            override fun pressedLeftButton() {

            }
        }
        initAction()
    }

    fun initAction () {
        coupon_menu_1.setOnClickListener {
            val parent = activity as MainActivity
            parent.startActivity(Intent(parent, CouponDetailActivity::class.java))
        }

        coupon_menu_2.setOnClickListener {

        }

        coupon_menu_3.setOnClickListener {

        }
    }


    companion object {
        var instance = CouponFragment()
    }

}
