package com.curling.kingdomofcurling.ui.fragment.travelinfo

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.curling.kingdomofcurling.R
import java.io.Serializable


class TravelInfoAdapter(val context:Context) :RecyclerView.Adapter<TravelInfoAdapter.TravelViewHolder>() {
    companion object {
        const val TravelInfoViewType = 1
        const val TravelCouponViewType = 2
    }
    var items:Array<TravelBase> = emptyArray()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TravelViewHolder {
        val inflater = LayoutInflater.from(context)
        return when (viewType) {
            TravelInfoViewType -> TravelInfoViewHolder(inflater.inflate(R.layout.cell_travel_info, parent, false))
            TravelCouponViewType -> TravelCouponViewHolder(inflater.inflate(R.layout.cell_travel_coupon, parent, false))
            else -> TravelInfoViewHolder(inflater.inflate(R.layout.cell_travel_info, parent, false))
        }
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int = items[position].viewType

    override fun onBindViewHolder(holder: TravelViewHolder, position: Int) {
        items[position].let {item->
            when(item.viewType) {
                TravelInfoViewType->{
                    val h = holder as TravelInfoViewHolder
                    val i = item as TravelInfo

                    h.title.text = i.title
                    h.content.text = i.content
                    //TODO Only test code
                    h.image.setImageResource(i.imageRes)

//                    h.image.setOnClickListener { item.clickEvent() }
                    h.layer.setOnClickListener { item.clickEvent() }
                    // TODO: make url info
//                    val requestOption = RequestOptions().skipMemoryCache(true)
//                    Glide.with(context).load(i.imageUrl).apply(requestOption).into(h.image)
                }
                TravelCouponViewType-> {
                    /**
                     * Nothing Todo ????
                     *
                     */
                }
                else->{}
            }
        }
    }



    /******************************************
     * VO
     *****************************************/
    open class TravelBase : Serializable{
        var viewType:Int = 0
    }

    class TravelInfo: TravelBase (){
        var imageUrl:String = ""
        var title:String = ""
        var content:String = ""
        // OnlyTestItem
        var imageRes:Int = 0
        var address:String = ""
        var info1:String = ""
        var info2: String = ""
        var lat:Double = com.curling.kingdomofcurling.ui.map.CurlingMapActivity.DefaultLat
        var lng:Double = com.curling.kingdomofcurling.ui.map.CurlingMapActivity.DefaultLng

        lateinit var clickEvent:()->Unit
    }

    /******************************************
     * ViewHolder
     *****************************************/
    open class TravelViewHolder(view: View?):RecyclerView.ViewHolder (view)

    class TravelInfoViewHolder(val view: View?) :TravelViewHolder(view) {
        lateinit var image:ImageView
        lateinit var title:TextView
        lateinit var content:TextView
        lateinit var layer:LinearLayout

        init {
            view?.let {
                image = it.findViewById(R.id.travel_image)
                title = it.findViewById(R.id.travel_title)
                content = it.findViewById(R.id.travel_content)
                layer = it.findViewById(R.id.layerTab)
            }
        }
    }

    class TravelCouponViewHolder(val view:View?) : TravelViewHolder(view)
}