package com.curling.kingdomofcurling.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.curling.kingdomofcurling.R
import com.curling.kingdomofcurling.ui.MainActivity
import com.curling.kingdomofcurling.util.getCurrentDate
import kotlinx.android.synthetic.main.fragment_check_booking.*


class CheckBookingFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_check_booking, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initParent()
    }

    fun initParent() {
        val parent = activity as MainActivity
        parent.setMainTitle("컬링장 조회")


        check_booking_date.text = getCurrentDate()
    }


    companion object {
        var instance:CheckBookingFragment = CheckBookingFragment()
    }
}
