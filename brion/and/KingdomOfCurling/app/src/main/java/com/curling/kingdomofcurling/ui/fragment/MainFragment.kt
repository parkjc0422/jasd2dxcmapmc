package com.curling.kingdomofcurling.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.curling.kingdomofcurling.R
import com.curling.kingdomofcurling.ui.MainActivity


class MainFragment:Fragment() {
    val title = ""
    val pageType = MainActivity.TitleType.Image

    public override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        view?.let {
            val reserve = it.findViewById<LinearLayout>(R.id.main_menu_reserve)
            reserve.setOnClickListener {
                Log.d("check", "checl")

                val parent = activity as MainActivity
                parent.setCurrentPage(ReservationFragment.newInstance())
            }

            val point = it.findViewById<LinearLayout>(R.id.main_menu_point)
            point.setOnClickListener {

            }
            val coupon = it.findViewById<LinearLayout>(R.id.main_menu_coupon)
            coupon.setOnClickListener {

            }
            val game = it.findViewById<LinearLayout>(R.id.main_menu_curling_game)
            game.setOnClickListener {

            }
        }

        var parent = activity as MainActivity
        parent.setMainTitle(title)
        parent.makeTitleInfo(pageType)
    }


    public override fun onCreateView(inflater:LayoutInflater?, container:ViewGroup?,
                                     savedInstanceState:Bundle?):View? {
        return inflater!!.inflate(R.layout.fragment_main, container, false)
    }

    companion object {
        var instance:MainFragment = MainFragment()
    }

}
