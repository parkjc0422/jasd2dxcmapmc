package com.curling.kingdomofcurling.ui.fragment.myinfo

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.curling.kingdomofcurling.R
import com.curling.kingdomofcurling.ui.MainActivity
import com.curling.kingdomofcurling.ui.MainActivityTitleListener
import com.curling.kingdomofcurling.ui.fragment.myinfo.adapter.MyPointAdapter
import kotlinx.android.synthetic.main.fragment_my_point.*

class MyPointUsageFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_point, container, false)
    }
    lateinit var parent:MainActivity
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        parent = activity as MainActivity

        initView()
    }


    fun initView() {

        myPointUsageMore.setOnClickListener {
            val item = hashMapOf(MyPointAdapter.MyPointHeader("2018년 1월") to arrayListOf(
                    MyPointAdapter.MyPointUsage(MyPointAdapter.PointUsageType.Earn, "+30,000P", "힐링게임 아이템" , "18.01.25"),
                    MyPointAdapter.MyPointUsage(MyPointAdapter.PointUsageType.Deduction, "-1,000P", "쿠폰 구입" , "18.01.25")
            ),MyPointAdapter.MyPointHeader("2018년 2월") to arrayListOf(
                    MyPointAdapter.MyPointUsage(MyPointAdapter.PointUsageType.Earn, "+30,000P", "힐링게임 아이템" , "18.02.25"),
                    MyPointAdapter.MyPointUsage(MyPointAdapter.PointUsageType.Deduction, "-1,000P", "쿠폰 구입" , "18.02.25")
            ))


            var adapter = myPointUsageView.adapter as MyPointAdapter
            for (key in item.keys) {
                adapter.items.add(key)
                val values = item[key]
                values?.let {
                    for(i in it) {
                        adapter.items.add(i)
                    }
                }
            }
            adapter.notifyDataSetChanged()
        }
        /**
         * TitleInfo
         */
        parent.makeTitleInfo(MainActivity.TitleType.SubTitle)
        parent.setMainTitle("내 포인트 내역")
        parent.setSubTitle("32,100p")
        parent.listener = object : MainActivityTitleListener {
            override fun pressedRightButton() {
            }

            override fun pressedLeftButton() {
                parent.setCurrentPage(back)
            }
        }

        /**
         * adapter
         */

        val item = hashMapOf(MyPointAdapter.MyPointHeader("2018년 1월") to arrayListOf(
                MyPointAdapter.MyPointUsage(MyPointAdapter.PointUsageType.Earn, "+30,000P", "힐링게임 아이템" , "18.01.25"),
                MyPointAdapter.MyPointUsage(MyPointAdapter.PointUsageType.Deduction, "-1,000P", "쿠폰 구입" , "18.01.25")
        ),MyPointAdapter.MyPointHeader("2018년 2월") to arrayListOf(
                MyPointAdapter.MyPointUsage(MyPointAdapter.PointUsageType.Earn, "+30,000P", "힐링게임 아이템" , "18.02.25"),
                MyPointAdapter.MyPointUsage(MyPointAdapter.PointUsageType.Deduction, "-1,000P", "쿠폰 구입" , "18.02.25")
        ))


        var adapter = MyPointAdapter(parent)
        for (key in item.keys) {
            adapter.items.add(key)
            val values = item[key]
            values?.let {
                for(i in it) {
                    adapter.items.add(i)
                }
            }
        }

//        myPointUsageView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        myPointUsageView.adapter = adapter
    }



    companion object {
        lateinit var back :Fragment
        var instance = MyPointUsageFragment()
    }

}
