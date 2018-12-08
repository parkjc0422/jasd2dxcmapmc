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
        parent.listener = object : MainActivityTitleListener {
            override fun pressedRightButton() {
                //TODO : ????
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
        for(i in 0 .. 10) {
            var item = MyCouponAdapter.MyCouponItem()
            myCouponAdapter.items.add(item)
        }
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
