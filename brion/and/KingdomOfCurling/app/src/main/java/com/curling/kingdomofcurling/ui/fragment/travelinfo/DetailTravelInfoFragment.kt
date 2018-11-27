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
import android.content.Intent
import com.curling.kingdomofcurling.ui.camera.CameraViewActivity
import com.curling.kingdomofcurling.ui.map.CurlingMapActivity


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
        detail_travel_info_info1.text = item.info1
        detail_travel_info_info2.text = item.info2
        detail_travel_info_address.text = item.address

        detail_travel_info_share.setOnClickListener {
            //TODO : what????????TODO?????
            val subject = "메시지 제목"
            val text = "메시지 내용은\n다음줄에서.."

            val intent = Intent(android.content.Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_SUBJECT, subject)
            intent.putExtra(Intent.EXTRA_TEXT, text)
            val chooser = Intent.createChooser(intent, "타이틀")
            startActivity(chooser)
        }

        detail_travel_info_menu1.setOnClickListener {
            startActivity(Intent(activity, CameraViewActivity::class.java))
        }

        detail_travel_info_menu2.setOnClickListener {
            var mapIntent = Intent(activity, CurlingMapActivity::class.java)
            mapIntent.putExtra(CurlingMapActivity.ADDRESS, item.address)
            mapIntent.putExtra(CurlingMapActivity.NAME, item.title)
            mapIntent.putExtra(CurlingMapActivity.LAT, item.lat)
            mapIntent.putExtra(CurlingMapActivity.LNG, item.lng)
            startActivity(mapIntent)
        }


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
        var instance = DetailTravelInfoFragment()
        var item: TravelInfoAdapter.TravelInfo? = null
    }

}
