package com.curling.kingdomofcurling.ui.fragment.coupon

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    }



    class MyCouponItem {
        var couponName:String = ""
        // TODO :make server or static image
//        var image:String = ""
        // TODO : remove
        var tempImage = ""
        lateinit var available:()->Unit
    }

    class MyCouponViewHolder(val view:View) :RecyclerView.ViewHolder (view){
        init {

        }
    }
}