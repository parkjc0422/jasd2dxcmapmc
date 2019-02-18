package com.curling.kingdomofcurling.ui.fragment.myinfo.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.curling.kingdomofcurling.R
import com.curling.kingdomofcurling.ui.fragment.booking.CheckBookingAdapter


class MyInfoAdapter(val context:Context) :RecyclerView.Adapter<MyInfoAdapter.MyInfoViewHolder>(){
    lateinit var items:Array<MyInfoItem>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyInfoViewHolder {
        val inflater = LayoutInflater.from(context)
        return MyInfoAdapter.MyInfoViewHolder(inflater.inflate(R.layout.cell_my_info, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MyInfoViewHolder, position: Int) {
        items[position].let { item ->
            holder.title.text = "${item.title}"
            holder.layer.setOnClickListener {
                item.listener()
            }
        }
    }

    class MyInfoViewHolder(val view:View) :RecyclerView.ViewHolder(view){
        var title:TextView
        var layer: LinearLayout
        init {
            title = view.findViewById(R.id.cell_my_info_title)
            layer = view.findViewById(R.id.cell_my_info_item)
        }
    }


    class MyInfoItem {
        var title:String
        var listener : ()->Unit

        constructor(title:String, listener: ()-> Unit) {
            this.title = title
            this.listener = listener
        }
    }
}