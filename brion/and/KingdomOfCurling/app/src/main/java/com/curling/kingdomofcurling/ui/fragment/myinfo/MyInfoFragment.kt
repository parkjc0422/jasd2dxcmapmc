package com.curling.kingdomofcurling.ui.fragment.myinfo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.curling.kingdomofcurling.R
import com.curling.kingdomofcurling.ui.MainActivity
import com.curling.kingdomofcurling.ui.MainActivityTitleListener
import com.curling.kingdomofcurling.ui.fragment.BookingFragment
import com.curling.kingdomofcurling.ui.fragment.booking.CheckResvFragment
import com.curling.kingdomofcurling.ui.fragment.coupon.MyCouponFragment
import com.curling.kingdomofcurling.ui.fragment.myinfo.adapter.MyInfoAdapter
import kotlinx.android.synthetic.main.fragment_my_info.*


class MyInfoFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_my_info, container, false)
    }
    lateinit var parent : MainActivity
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        parent = activity as MainActivity

        parent.makeTitleInfo(MainActivity.TitleType.MyPage)
        parent.setMainTitle("마이페이지")
        parent.listener = object : MainActivityTitleListener{
            override fun pressedRightButton() {}

            override fun pressedLeftButton() {
                parent.setCurrentPage(back as Fragment)
            }
        }

        initView()
    }

    fun initView () {
        val items = arrayOf(
                MyInfoAdapter.MyInfoItem("컬링장 예약확인") {
                    CheckResvFragment.back = BookingFragment.newInstance()
                    parent.setCurrentPage(CheckResvFragment.instance)
                },
                MyInfoAdapter.MyInfoItem("내쿠폰 보기") {
                    var instance = MyCouponFragment.instance
                    MyCouponFragment.backState = this
                    parent.setCurrentPage(instance)
                },
                MyInfoAdapter.MyInfoItem("내 포인트 내역") {
                    MyPointUsageFragment.back = MyCouponFragment.instance
                    parent.setCurrentPage(MyPointUsageFragment.instance)
                },
                MyInfoAdapter.MyInfoItem("회원정보 변경") {

                }
        )


        val adapter = MyInfoAdapter(parent)
        adapter.items = items
        myInfoCategory.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        myInfoCategory.adapter = adapter
    }



    companion object {
        private var back:Fragment? = null
        fun getInstance (back:Fragment) :MyInfoFragment{
            MyInfoFragment.back = back
            return MyInfoFragment()
        }
    }
}