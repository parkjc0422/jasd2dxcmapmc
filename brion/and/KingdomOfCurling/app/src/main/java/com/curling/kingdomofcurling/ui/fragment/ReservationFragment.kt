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


class ReservationFragment : Fragment() {

    var totalMember: Int = 0
    var time:Int = 0

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
            parent.setCurrentPage(MainFragment.instance)
        }

        initParent()
    }

    override fun onDestroy() {
        super.onDestroy()
        (activity as MainActivity).listener = null
    }

    fun setTime (plus: Boolean) {
        if(!plus) {
            if(time == 0) {
                time = 0
            } else {
                time -= 1
            }
        } else {
            time += 1
        }
        reserve_time.text = "$time 시간"
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
        reserve_total_member.text = "$totalMember 시간"
    }


    fun initParent () {
        val parent = activity as MainActivity

        parent.makeTitleInfo(MainActivity.TitleType.Title)
        parent.setMainTitle(resources.getString(R.string.reservation))

        parent.listener = object :MainActivityTitleListener{
            override fun pressedRightButton() {
                //TODO: nothing todo
            }

            override fun pressedLeftButton() {
                parent.setCurrentPage(MainFragment.instance)
            }
        }
    }



    companion object {
        fun newInstance(): ReservationFragment = ReservationFragment()
    }
}
