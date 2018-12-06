package com.curling.kingdomofcurling.ui.fragment.booking

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.curling.kingdomofcurling.R

class CheckResvAdapter(val context:Context) :RecyclerView.Adapter<CheckResvAdapter.CheckResvViewHolder>(){
    var items: MutableList<CheckResvItem> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckResvViewHolder {
        val inflater = LayoutInflater.from(context)
        return CheckResvViewHolder(inflater.inflate(R.layout.cell_check_resrv, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CheckResvViewHolder, position: Int) {
        items[position].let {
            holder.date.text = it.date
            holder.startTime.text = it.startTime
            holder.endTime.text = it.endTime
            holder.numberOfMember.text = it.numberOfMember
            holder.cancel.text = "예약 취소"

            holder.cancel.setOnClickListener {view ->
                it.cancel(it.id)
            }
        }
    }

    fun removeItem (item:CheckResvItem) {
        items.remove(item)
    }

    class CheckResvItem {
        var id:Int= -1
        var date:String = ""
        var startTime : String = ""
        var endTime:String = ""
        var numberOfMember:String = ""
        lateinit var cancel: (Int)->Unit
    }

    class CheckResvViewHolder(val view:View) :RecyclerView.ViewHolder(view){
        var date:TextView
        var startTime:TextView
        var endTime:TextView
        var numberOfMember:TextView
        var cancel:TextView

        init {
            date = view.findViewById(R.id.cell_resv_date)
            startTime = view.findViewById(R.id.cell_resv_start_time)
            endTime = view.findViewById(R.id.cell_resv_end_time)
            numberOfMember = view.findViewById(R.id.cell_resv_member)
            cancel = view.findViewById(R.id.cell_resv_cancel)
        }
    }
}