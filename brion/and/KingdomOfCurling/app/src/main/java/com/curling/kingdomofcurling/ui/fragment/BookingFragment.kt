package com.curling.kingdomofcurling.ui.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.curling.kingdomofcurling.R
import com.curling.kingdomofcurling.ui.MainActivity
import com.curling.kingdomofcurling.ui.MainActivityTitleListener
import com.curling.kingdomofcurling.ui.fragment.booking.CheckBookingFragment
import com.curling.kingdomofcurling.ui.fragment.booking.CheckResvFragment
import com.curling.kingdomofcurling.ui.fragment.myinfo.MyInfoFragment
import kotlinx.android.synthetic.main.fragment_booking.*


class BookingFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_booking, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        booking_menu_1.setOnClickListener {
            val parent = activity as MainActivity
            parent.setCurrentPage(CheckBookingFragment.instance)
        }
        booking_menu_2.setOnClickListener {
            val parent = activity as MainActivity
            CheckResvFragment.back = newInstance()
            parent.setCurrentPage(CheckResvFragment.instance)
        }
        booking_menu_3.setOnClickListener {  }

        initParentTitle()
    }

    fun initParentTitle () {
        val parent = activity as MainActivity
        parent.makeTitleInfo(MainActivity.TitleType.Title)
        parent.setMainTitle(resources.getString(R.string.curling_booking))

        parent.listener = object :MainActivityTitleListener {
            override fun pressedRightButton() {
                parent.setCurrentPage(MyInfoFragment.getInstance(newInstance()))
            }

            override fun pressedLeftButton() {

            }
        }

    }


    companion object {
        val fTag = "BookingFragment"

        fun newInstance(): BookingFragment = BookingFragment()
    }

}
