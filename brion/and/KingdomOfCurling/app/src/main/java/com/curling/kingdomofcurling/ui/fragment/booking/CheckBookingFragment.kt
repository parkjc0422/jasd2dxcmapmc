package com.curling.kingdomofcurling.ui.fragment.booking

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
import com.curling.kingdomofcurling.ui.fragment.ReservationFragment
import com.curling.kingdomofcurling.ui.fragment.myinfo.MyInfoFragment
import com.curling.kingdomofcurling.util.getCurrentDate
import kotlinx.android.synthetic.main.fragment_check_booking.*


class CheckBookingFragment : Fragment() {
    lateinit var checkBookingAdapter: CheckBookingAdapter
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

        checkBookingAdapter = CheckBookingAdapter(parent)

        val item = CheckBookingAdapter.CheckBookingItem()
        item.time = "09:00"
        item.minStatus = 40
        item.maxStatus = 40
        item.status = CheckBookingAdapter.CheckBookingItem.Status.Imposible
        item.clickEvent = {
            ReservationFragment.lastFragment = this
            parent.setCurrentPage(ReservationFragment.newInstance())
        }

        val item2 = CheckBookingAdapter.CheckBookingItem()
        item2.time = "10:00"
        item2.minStatus = 38
        item2.maxStatus = 40
        item2.status = CheckBookingAdapter.CheckBookingItem.Status.Possible
        item2.clickEvent = {
            ReservationFragment.lastFragment = this
            parent.setCurrentPage(ReservationFragment.newInstance())
        }

        parent.listener = object :MainActivityTitleListener {
            override fun pressedRightButton() {
                parent.setCurrentPage(MyInfoFragment.getInstance(instance))
            }

            override fun pressedLeftButton() {
                parent.setCurrentPage(BookingFragment.newInstance())
            }
        }

        checkBookingAdapter.items = arrayOf(item, item2)

        checkBookingList.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        checkBookingList.adapter = checkBookingAdapter
        check_booking_date.text = getCurrentDate()

    }

    override fun onDestroy() {
        super.onDestroy()
        (activity as MainActivity).listener = null
    }


    companion object {
        var instance: CheckBookingFragment = CheckBookingFragment()
    }
}
