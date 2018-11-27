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
            item1.address = "경상북도 의성군 금성면 대리리 351"
            item1.info1 = "삼한시대의 부족국가였던 조문국의 경덕왕릉으로 추정되는 능으로 경상북도 의성군 금성면 대리리에 있다. "
            item1.info2 = "경상북도 의성군 금성면의 대리리·학미리·탑리리 일대에는 경상북도기념물 제128호로 지정된 ‘의성 금성산 고분군’ 200여 기가 있는데, 대리리의 고분군 중에 조문국 경덕왕릉이라고 추정되는 무덤이 있다. 삼한시대의 부족국가였던 조문국(召文國)은 현재의 경상북도 의성군 금성면 일대를 도읍지로 하여 존속하다가 185년(신라 벌휴왕 2년)에 신라에 병합되었다고 한다. 하지만 조문국이 실재했었다는 기록은 <삼국사기>에 짧게 언급되어 있을 뿐 문헌자료는 거의 남아 있지 않다. \n" +
                    "\n" +
                    "1725년(영조 원년) 현령 이우신이 경덕왕릉을 증축하고 하마비 등을 세웠다고 하는데 그때부터 왕릉제사를 지내오다가 일제강점기에 중단되었고, 그후 경덕왕릉보존회가 구성되어 다시 제사를 지내고 있다. 소나무로 둘러싸인 묘역은 ‘조문국경덕왕릉’이라고 쓰여진 비석과 문인석·장명등·상석으로 단장되어 있다.\n" +
                    "\n" +
                    "[네이버 지식백과] 조문국 경덕왕릉 [召文國 景德王陵] (두산백과)\n" +
                    "\n"

            item1.imageRes = R.drawable.jomoon
            item1.viewType = TravelInfoAdapter.TravelInfoViewType
            item1.clickEvent = {
                val parent = activity as MainActivity
                var detailTravelInfoFragment = DetailTravelInfoFragment.instance
                detailTravelInfoFragment.item = item1
                parent.setCurrentPage(detailTravelInfoFragment)
            }

            item2.title = "금봉자연휴양림"
            item2.content = "의성군 옥산면 금봉리에 위치한 의성군 유일의 자연휴양림"
            item2.imageUrl = ""
            item2.address = "경상북도 의성군 옥산면 금봉리 산24-1번지에 있는 자연휴양림"
            item2.info1 = "경상북도 의성군 옥산면 금봉리 산24-1번지에 있는 자연휴양림."
            item2.info2 = "금봉산 자락에 있는 청정자연 휴식공간으로서 의성군에서 2004년 조성하였다. 휴양림 내에는 산림휴양관, 복합수련관, 숲속의 집, 통나무산막과 다양한 숲속 체험을 제공하는 야영데크, 캠프화이어장, 샤워장, 취사장 등이 설치되어 있다. 이 가운데 복합수련관은 세미나실, 대회의실, 소규모공연장 등을 갖추고 있어 단체행사가 가능하고 통나무산막은 참나무, 소나무, 가문비나무, 자작나무 등 다양한 식생의 나무들로 이루어져 자연의 향취를 느낄 수 있다. 이밖에 분수와 무지개다리를 관망할 수 있는 팔각정과 휴양림을 감싸고 있는 6.4km의 등산로 2코스, 7.4km의 청석골 산책로 및 시화산길, 단거리 산책로 등도 조성되어 있다. 주변 관광지로는 빙계계곡, 고운사, 사촌리 가로숲, 공룡발자국화석 등이 있다.\n" +
                    "\n" +
                    "[네이버 지식백과] 금봉자연휴양림 [金鳳自然休養林] (두산백과)\n" +
                    "\n"
            item2.imageRes = R.drawable.goombong
            item2.viewType = TravelInfoAdapter.TravelInfoViewType
            item2.clickEvent = {
                val parent = activity as MainActivity
                var detailTravelInfoFragment = DetailTravelInfoFragment.instance
                detailTravelInfoFragment.item = item2
                parent.setCurrentPage(detailTravelInfoFragment)
            }

            item3.title = "고운사"
            item3.content = "저승의 염라대왕이 묻는다. \"자네, 고운사라고 들어봤는가??몰라라라라라랄\" "
            item3.imageUrl = ""
            item3.address = "경북 의성군 단촌면 고운사길 415(구계리 116)"
            item3.info1 = "경상북도 의성군 단촌면(丹村面) 등운산(騰雲山)에 있는 절."
            item3.info2 = "681년(신문왕 1) 신라시대 고승인 의상(義湘)이 창건하여 이름을 고운사(高雲寺)라고 하였는데, 그후 최치원(崔致遠)이 여지(如智)·여사(如事) 두 승려와 함께 가허루(駕虛樓)와 우화루(羽化樓)를 짓고 고운사(孤雲寺)로 개칭하였다. 헌강왕 때에 도선(道詵)이 약사여래불과 석탑을 건립하였다.\n" +
                    "\n" +
                    "고려시대인 948년(정종 3) 고려의 운주조통(雲住照通)이 사찰을 중창하였고, 1018년(현종 9)에 천우(天祐)가 다시 중창하였다. 이후 조선시대에 들어와 1695년(숙종 21)에 행옥(幸玉)과 태운(泰運)이 중수하였다. 그후 1835년(헌종 1)에 불탄 것을 만송(晩松)·호암(虎巖)·수열(守悅) 등이 재건하였다. 대한불교조계종 제16교구 본사이며 의성, 안동, 영주, 봉화, 영양 등지의 사찰을 관할하고 있다. 사찰내 약사전과 석가여래좌상이 보물급 문화재로 지정되어 있다.\n" +
                    "[네이버 지식백과] 고운사 [孤雲寺] (두산백과)\n" +
                    "\n"
            item3.imageRes = R.drawable.gounsa
            item3.viewType = TravelInfoAdapter.TravelInfoViewType
            item3.clickEvent = {
                val parent = activity as MainActivity
                var detailTravelInfoFragment = DetailTravelInfoFragment.instance
                detailTravelInfoFragment.item = item3
                parent.setCurrentPage(detailTravelInfoFragment)
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
