package com.curling.kingdomofcurling.ui.fragment.travelinfo


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.curling.kingdomofcurling.R
import com.curling.kingdomofcurling.ui.MainActivity
import com.curling.kingdomofcurling.ui.MainActivityTitleListener
import com.curling.kingdomofcurling.ui.fragment.TravelInfoFragment
import kotlinx.android.synthetic.main.fragment_detail_travel_info.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val TRAVEL_IFNO_ITEM_VO = "param1"

/**
 * A simple [Fragment] subclass.
 *
 */
class DetailTravelInfoFragment : Fragment() {
    lateinit var item: TravelInfoAdapter.TravelInfo
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_travel_info, container, false)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            item = it.getSerializable(TRAVEL_IFNO_ITEM_VO) as TravelInfoAdapter.TravelInfo
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initParent()

        initComponent()
    }

    fun initComponent () {
        // TODO : REMOVE CODE
        detail_travel_info_image.setImageResource(item.imageRes)
        detail_travel_info_title.text = item.title


    }

    fun initParent (){
        val parent = activity as MainActivity
        parent.makeTitleInfo(MainActivity.TitleType.Title)
        parent.setMainTitle(item.title as String)
        parent.listener = object : MainActivityTitleListener{
            override fun pressedRightButton() {

            }

            override fun pressedLeftButton() {
                parent.setCurrentPage(TravelInfoFragment.instance)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        val parent = activity as MainActivity
        parent.listener = null
    }


    companion object {
        fun newInstance(item:TravelInfoAdapter.TravelInfo) :DetailTravelInfoFragment{
            var fragment = DetailTravelInfoFragment()
            var bundle = Bundle()
            bundle.putSerializable(TRAVEL_IFNO_ITEM_VO, item)
            fragment.arguments = bundle
            return fragment
        }
    }

}
