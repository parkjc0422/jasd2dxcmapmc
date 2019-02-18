package com.curling.kingdomofcurling.ui.fragment.coupon


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.curling.kingdomofcurling.R
import com.curling.kingdomofcurling.ui.MainActivity
import com.curling.kingdomofcurling.ui.MainActivityTitleListener
import com.curling.kingdomofcurling.ui.fragment.myinfo.MyInfoFragment
import com.curling.kingdomofcurling.ui.fragment.travelinfo.DetailTravelInfoFragment
import kotlinx.android.synthetic.main.fragment_my_coupon.*


/**
 * A simple [Fragment] subclass.
 *
 */
class MyCouponFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_coupon, container, false)
    }

    lateinit var myCouponAdapter: MyCouponAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val parent = activity as MainActivity

        parent.setMainTitle(resources.getString(R.string.my_coupon_title))
        parent.makeTitleInfo(type = MainActivity.TitleType.Title)
        parent.listener = object : MainActivityTitleListener {
            override fun pressedRightButton() {
                parent.setCurrentPage(MyInfoFragment.getInstance(DetailTravelInfoFragment.instance))
            }

            override fun pressedLeftButton() {
                val back = backState as Fragment
                val parent = activity as MainActivity

                backState = null
                parent.setCurrentPage(back)
            }
        }

        initComponent(parent)

    }

    fun makeCouponData ( ){
        var item = MyCouponAdapter.MyCouponItem()
        item.couponName = resources.getString(R.string.coupon_1000p)
        item.enable = true
        item.tempImage = R.drawable.ic_coupon01
        myCouponAdapter.items.add(item)


        var item1 = MyCouponAdapter.MyCouponItem()
        item1.couponName = resources.getString(R.string.coupon_3000p)
        item1.enable = true
        item1.tempImage = R.drawable.ic_coupon02
        myCouponAdapter.items.add(item1)


        var item2 = MyCouponAdapter.MyCouponItem()
        item2.couponName = resources.getString(R.string.coupon_5000p)
        item2.enable = true
        item2.tempImage = R.drawable.ic_coupon03
        myCouponAdapter.items.add(item2)


        var item3 = MyCouponAdapter.MyCouponItem()
        item3.couponName = resources.getString(R.string.coupon_1000p)
        item3.enable = false
        item3.tempImage = R.drawable.ic_coupon04
        myCouponAdapter.items.add(item3)
    }

    fun initComponent (parent:MainActivity) {
        var gridLayoutManager = GridLayoutManager(parent , 2)
        myCouponList.layoutManager = gridLayoutManager
        myCouponList.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        myCouponList.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL))
        myCouponAdapter = MyCouponAdapter(parent)
        makeCouponData()


        myCouponList.adapter =  myCouponAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        val parent = activity as MainActivity
        parent.listener = null
    }







    companion object {
        var backState: Fragment? = null
        var instance = MyCouponFragment()
    }
}
