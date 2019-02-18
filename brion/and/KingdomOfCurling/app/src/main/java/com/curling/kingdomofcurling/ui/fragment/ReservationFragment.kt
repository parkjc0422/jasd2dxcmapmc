package com.curling.kingdomofcurling.ui.fragment

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.curling.kingdomofcurling.R
import kotlinx.android.synthetic.main.fragment_reservation.*
import android.app.TimePickerDialog
import android.content.Context
import com.curling.kingdomofcurling.ui.dialog.showDatePickerCurrent
import com.curling.kingdomofcurling.ui.dialog.showTimePickerCurrent
import com.curling.kingdomofcurling.ui.MainActivity
import com.curling.kingdomofcurling.ui.MainActivityTitleListener
import com.curling.kingdomofcurling.ui.fragment.myinfo.MyInfoFragment
import com.curling.kingdomofcurling.ui.fragment.travelinfo.DetailTravelInfoFragment


class ReservationFragment : Fragment() {

    var totalMember: Int = 0
    var timeHour: Int = 0
    var timeMinute:Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_reservation, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        reserve_schedule_date.setOnClickListener {
            showDatePickerCurrent(context as Context, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                reserve_schedule_date.text = "$year - $monthOfYear - $dayOfMonth"
            })
        }

        reserve_schedule_time.setOnClickListener {
            showTimePickerCurrent(context as Context, TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                reserve_schedule_time.text = "$hourOfDay : $minute"
            })
        }

        reserve_time_minus.setOnClickListener {
            setTime(false)
        }

        reserve_time_plus.setOnClickListener {
            setTime(true)
        }

        reserve_total_member_min.setOnClickListener {
            setMember(false)
        }

        reserve_total_member_plus.setOnClickListener {
            setMember(true)
        }

        reserve_commit.setOnClickListener {
            val parent = activity as MainActivity
            parent.setCurrentPage(lastFragment)
        }

        initParent()
    }

    override fun onDestroy() {
        super.onDestroy()
        (activity as MainActivity).listener = null
    }

    fun setTime (plus: Boolean) {

        if(plus) { // plus
            if(timeHour == 0) {
                // 0 -> 1
                timeHour = 1
            } else if (timeMinute) {
                // h.5 -> h + 1
                timeMinute = false
                timeHour += 1
            } else {
                // h -> h.5
                timeMinute = true
            }
        } else { // minus
            if (timeHour == 0) {
                // 0 -> 0
                timeHour = 0
                timeMinute = false
            } else if(timeHour == 1) {
                if(timeMinute) {
                    // 1.5 -> 1
                    timeHour = 1
                    timeMinute = false
                } else {
                    //1 -> 0
                    timeHour = 0
                    timeMinute = false
                }
            } else {
                if(timeMinute) {
                    timeMinute = false
                } else {
                    timeHour -= 1
                    timeMinute = true
                }
            }
        }

        if(!timeMinute)
            reserve_time.text = "$timeHour 시간"
        else {
            reserve_time.text = "$timeHour 시간 30 분"
        }
    }

    fun setMember (plus: Boolean) {
        if(!plus) {
            if(totalMember == 0) {
                totalMember = 0
            } else {
                totalMember -= 1
            }
        } else {
            totalMember += 1
        }
        reserve_total_member.text = "$totalMember 명"
    }


    fun initParent () {
        val parent = activity as MainActivity

        parent.makeTitleInfo(MainActivity.TitleType.Title)
        parent.setMainTitle(resources.getString(R.string.reservation))

        parent.listener = object :MainActivityTitleListener{
            override fun pressedRightButton() {
                parent.setCurrentPage(MyInfoFragment.getInstance(newInstance()))
            }

            override fun pressedLeftButton() {
                parent.setCurrentPage(lastFragment)
            }
        }
    }



    companion object {
        lateinit var lastFragment:Fragment
        fun newInstance(): ReservationFragment = ReservationFragment()
    }
}
