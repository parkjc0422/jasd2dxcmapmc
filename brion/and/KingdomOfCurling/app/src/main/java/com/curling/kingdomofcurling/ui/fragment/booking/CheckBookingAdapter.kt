package com.curling.kingdomofcurling.ui.fragment.booking

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.curling.kingdomofcurling.R
import com.curling.kingdomofcurling.util.getColor

class CheckBookingAdapter(val context:Context): RecyclerView.Adapter<CheckBookingAdapter.CheckBookingViewHolder> (){
    var items:Array<CheckBookingItem> = emptyArray()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckBookingViewHolder {
        val inflater = LayoutInflater.from(context)
        return CheckBookingViewHolder(inflater.inflate(R.layout.cell_booking_check, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CheckBookingViewHolder, position: Int) {
        items[position].let {
            holder.time.text = it.time
            holder.condition.text = "${it.minStatus}/${it.maxStatus}"
            when (it.status) {
                CheckBookingItem.Status.Imposible -> {
                    holder.available.text = context.resources.getString(R.string.impossible)
                    holder.available.setTextColor(getColor(context,R.color.impossible_color))
                    holder.status.text = context.resources.getString(R.string.soldout)
                    holder.status.setTextColor(getColor(context,R.color.sold_out))
                    holder.status.isClickable = false
                }
                CheckBookingItem.Status.Possible ->{
                    holder.available.text = context.resources.getString(R.string.possible)
                    holder.available.setTextColor(getColor(context, R.color.possible_color))
                    holder.status.text = context.resources.getString(R.string.remain)
                    holder.status.setTextColor(getColor(context,R.color.reserve))
                }
            }
        }
        holder.status.setOnClickListener {
            items[position].clickEvent()
        }
    }

    class CheckBookingViewHolder(val view:View):RecyclerView.ViewHolder(view) {
        var time:TextView
        var condition:TextView
        var available:TextView
        var status:TextView
        init {
            time = view.findViewById(R.id.cell_booking_time)
            condition = view.findViewById(R.id.cell_booking_condition)
            available = view.findViewById(R.id.cell_booking_available)
            status = view.findViewById(R.id.cell_booking_status)
        }
    }


    class CheckBookingItem {
        enum class Status {
            Possible, Imposible;
        }

        lateinit var time:String
        var minStatus:Int = 0
        var maxStatus:Int = 0
        lateinit var status:Status

        lateinit var clickEvent:()->Unit
    }
}