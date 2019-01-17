package com.curling.kingdomofcurling.ui.fragment.coupon

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.curling.kingdomofcurling.R
import java.util.zip.Inflater

class MyCouponAdapter(val context: Context) :RecyclerView.Adapter<MyCouponAdapter.MyCouponViewHolder>(){
    var items :ArrayList<MyCouponItem> = arrayListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCouponViewHolder {
        val inflater = LayoutInflater.from(context)
        return MyCouponViewHolder(inflater.inflate(R.layout.cell_my_coupon, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MyCouponViewHolder, position: Int) {
        items[position].let {
            holder.image.setImageResource(it.tempImage)
            holder.textview.text = "${it.couponName}"
            holder.button.isEnabled = it.enable
            if(it.enable) {
                holder.button.setTextColor(context.resources.getColor(R.color.white))
                holder.textview.setTextColor(context.resources.getColor(R.color.coupon_enable))
                holder.button.text = "${context.resources.getString(R.string.coupon_enable_string)}"
            } else {
                holder.button.setTextColor(context.resources.getColor(R.color.btn_oval_color_dim))
                holder.textview.setTextColor(context.resources.getColor(R.color.coupon_disable))
                holder.button.text = "${context.resources.getString(R.string.coupon_disable_string)}"
            }
        }
    }



    class MyCouponItem {
        var couponName:String = ""
        // TODO :make server or static image
//        var image:String = ""
        // TODO : remove
        var tempImage = 0
        lateinit var available:()->Unit
        var enable:Boolean = true
    }

    class MyCouponViewHolder(val view:View) :RecyclerView.ViewHolder (view){
        var button:Button
        var textview:TextView
        var image:ImageView
        init {
            button = view.findViewById(R.id.coupon_available)
            textview = view.findViewById(R.id.coupon_name)
            image = view.findViewById(R.id.coupon_image)
        }
    }
}