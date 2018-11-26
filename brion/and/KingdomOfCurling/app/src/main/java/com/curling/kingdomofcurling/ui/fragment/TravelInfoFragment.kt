package com.curling.kingdomofcurling.ui.fragment


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView

import com.curling.kingdomofcurling.R
import com.curling.kingdomofcurling.ui.MainActivity
import com.curling.kingdomofcurling.ui.MainActivityTitleListener
import com.curling.kingdomofcurling.ui.fragment.travelinfo.DetailTravelInfoFragment
import com.curling.kingdomofcurling.ui.fragment.travelinfo.TravelInfoAdapter
import kotlinx.android.synthetic.main.fragment_travel_info.*

class TravelInfoFragment : Fragment() {
    lateinit var travelInfoAdapter: TravelInfoAdapter

    var currentStep = 0
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_travel_info, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initParent()
        initAction()
    }

    fun initParent () {
        val parent = activity as MainActivity
        parent.setMainTitle(resources.getString(R.string.travel_info_title))
        parent.makeTitleInfo(MainActivity.TitleType.Title)

        parent.listener = object :MainActivityTitleListener {
            override fun pressedRightButton() {
                //TODO : what todo?
            }

            override fun pressedLeftButton() {
                if(travel_info_main_menu_list.visibility == View.GONE) {
                    travel_info_main_menu_list.visibility = View.VISIBLE
                    travel_info_list.visibility = View.GONE
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        (activity as MainActivity).listener = null
    }

    fun initAction () {
        travel_info_menu_1.setOnClickListener {
//TODO : remake after server connection
            travelInfoAdapter = TravelInfoAdapter(context as Context)

            var item1 = TravelInfoAdapter.TravelInfo()
            var item2 = TravelInfoAdapter.TravelInfo()
            var item3 = TravelInfoAdapter.TravelInfo()

            item1.title = "조문국사적지"
            item1.content = "아스라한 그 이름 '조문국'"
            item1.imageUrl = ""
            item1.imageRes = R.drawable.jomoon
            item1.viewType = TravelInfoAdapter.TravelInfoViewType
            item1.clickEvent = {
                val parent = activity as MainActivity
                parent.setCurrentPage(DetailTravelInfoFragment.newInstance(item1))
            }

            item2.title = "금봉자연휴양림"
            item2.content = "의성군 옥산면 금봉리에 위치한 의성군 유일의 자연휴양림"
            item2.imageUrl = ""
            item2.imageRes = R.drawable.goombong
            item2.viewType = TravelInfoAdapter.TravelInfoViewType
            item2.clickEvent = {
                val parent = activity as MainActivity
                parent.setCurrentPage(DetailTravelInfoFragment.newInstance(item2))
            }

            item3.title = "고운사"
            item3.content = "저승의 염라대왕이 묻는다. \"자네, 고운사라고 들어봤는가??몰라라라라라랄\" "
            item3.imageUrl = ""
            item3.imageRes = R.drawable.gounsa
            item3.viewType = TravelInfoAdapter.TravelInfoViewType
            item3.clickEvent = {
                val parent = activity as MainActivity
                parent.setCurrentPage(DetailTravelInfoFragment.newInstance(item3))
            }

            travelInfoAdapter.items = arrayOf(item1, item2, item3)

            travel_info_list.adapter = travelInfoAdapter
            travel_info_main_menu_list.visibility = View.GONE
            travel_info_list.visibility = View.VISIBLE
            travel_info_list.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            travelInfoAdapter.notifyDataSetChanged()



        }

        travel_info_menu_2.setOnClickListener {

        }
    }


    companion object {
        var instance:TravelInfoFragment = TravelInfoFragment()
    }

}
