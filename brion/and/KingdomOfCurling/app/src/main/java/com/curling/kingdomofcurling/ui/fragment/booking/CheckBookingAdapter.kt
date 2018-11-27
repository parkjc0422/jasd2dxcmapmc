package com.curling.kingdomofcurling.ui.fragment.booking

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class CheckBookingAdapter(val context:Context): RecyclerView.Adapter<CheckBookingAdapter.CheckBookingViewHolder> (){
    var items:Array<CheckBookingItem> = emptyArray()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckBookingViewHolder {

    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CheckBookingViewHolder, position: Int) {

    }

    class CheckBookingViewHolder(val view:View):RecyclerView.ViewHolder(view) {
        lateinit var time:TextView
        lateinit var condition:TextView
        lateinit var available:TextView
        lateinit var status:TextView
        init {

        }
    }


    class CheckBookingItem {

    }
}