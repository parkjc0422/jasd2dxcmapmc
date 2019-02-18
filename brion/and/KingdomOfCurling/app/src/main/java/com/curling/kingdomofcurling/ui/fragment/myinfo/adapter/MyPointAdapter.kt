package com.curling.kingdomofcurling.ui.fragment.myinfo.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.curling.kingdomofcurling.R
import kotlinx.android.synthetic.main.fragment_email_login.view.*

class MyPointAdapter (val context:Context):RecyclerView.Adapter<MyPointAdapter.MyPointViewHolder> (){
    val VIEW_KEY = 1
    val VIEW_ITEM = 2
    var items:MutableList<PointUsageItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPointViewHolder {
        val inflater = LayoutInflater.from(context)
        if(viewType == VIEW_KEY)
            return MyPointAdapter.MyPointHeaderViewHolder(inflater.inflate(R.layout.cell_my_point_header, parent, false))
        return MyPointAdapter.MyPointItemViewholder(inflater.inflate(R.layout.cell_my_point_usage, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MyPointViewHolder, position: Int) {
        if(items[position] is MyPointUsage) {
            // Item
            val item = items[position] as MyPointUsage
            (holder as MyPointItemViewholder).let {
                it.content.text = item.content
                it.date.text = item.date
                it.point.text = item.point
                if(item.type == PointUsageType.Earn) {
                    it.point.setTextColor(context.resources.getColor(R.color.top_title))
                } else {
                    it.point.setTextColor(context.resources.getColor(R.color.colorAccent))
                }
            }
        } else if(items[position] is MyPointHeader){
            // Header
            val item = items[position] as MyPointHeader
            (holder as MyPointHeaderViewHolder).let {
                it.header.text = item.date
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if(items[position] is MyPointHeader) return VIEW_KEY
        else return VIEW_ITEM
    }



    open class MyPointViewHolder(view:View) :RecyclerView.ViewHolder(view)
    class MyPointItemViewholder(val view:View):MyPointViewHolder(view){
        var content:TextView
        var point :TextView
        var date:TextView

        init {
            content = view.findViewById(R.id.myPointUsageContent)
            point = view.findViewById(R.id.myPointUsageValue)
            date = view.findViewById(R.id.myPointUsageDate)
        }
    }
    class MyPointHeaderViewHolder (val view:View) :MyPointViewHolder(view) {
        var header:TextView
        init {
            header = view.findViewById(R.id.cell_my_point_header)
        }
    }


    enum class PointUsageType {
        Deduction, Earn
    }
    open class PointUsageItem

    class MyPointHeader(
            val date:String
    ): PointUsageItem()

    class MyPointUsage(
            val type:PointUsageType,
            val point: String,
            val content:String,
            val date:String
    ):PointUsageItem()

}