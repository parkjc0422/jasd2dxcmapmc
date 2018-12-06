package com.curling.kingdomofcurling.ui.fragment.booking


import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker

import com.curling.kingdomofcurling.R
import com.curling.kingdomofcurling.ui.MainActivity
import com.curling.kingdomofcurling.ui.MainActivityTitleListener
import com.curling.kingdomofcurling.ui.dialog.showDatePickerCurrent
import com.curling.kingdomofcurling.ui.fragment.BookingFragment
import com.curling.kingdomofcurling.util.getCurrentDate
import kotlinx.android.synthetic.main.fragment_check_resv.*

class CheckResvFragment : Fragment() {

    lateinit var adapter: CheckResvAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_check_resv, container, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        val parent = activity as MainActivity
        parent.listener = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val parent = activity as MainActivity

        parent.listener = object : MainActivityTitleListener{
            override fun pressedRightButton() {
                //TODO:WHAT?
            }

            override fun pressedLeftButton() {
                parent.setCurrentPage(BookingFragment.newInstance())
            }
        }


        adapter  = CheckResvAdapter(parent)
        parent.setMainTitle("예약 확인")
        check_resv_date.text = getCurrentDate()
        checkResvList.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        check_resv_date.setOnClickListener {
            showDatePickerCurrent(parent, object :DatePickerDialog.OnDateSetListener{
                override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
                    check_resv_date.text = "${p1}년 ${p2+1}월  ${p3}일"
                    initData()
                }
            })
        }

        initData()

    }

    fun removeItem (item :CheckResvAdapter.CheckResvItem) {
        adapter.removeItem(item)
        adapter.notifyDataSetChanged()
    }

    fun initData () {
        adapter.items = mutableListOf()

        var item = CheckResvAdapter.CheckResvItem()
        item.date = "12.30"
        item.startTime = "09:00"
        item.endTime = "2시간"
        item.numberOfMember = "4명"
        item.id = 0
        item.cancel = {
            removeItem(item)
        }

        var item2 = CheckResvAdapter.CheckResvItem()
        item2.date = "12.31"
        item2.startTime = "19:00"
        item2.endTime = "1시간"
        item2.numberOfMember = "2명"
        item2.id = 1
        item2.cancel = {
            removeItem(item2)
        }

        adapter.items.add(item)
        adapter.items.add(item2)

        checkResvList.adapter = adapter

    }

    companion object {
        var instance = CheckResvFragment()
    }

}
