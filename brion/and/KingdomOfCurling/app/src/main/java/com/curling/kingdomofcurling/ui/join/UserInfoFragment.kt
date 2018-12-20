package com.curling.kingdomofcurling.ui.join


import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.curling.kingdomofcurling.R
import com.curling.kingdomofcurling.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_user_info.*

class UserInfoFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_info, container, false)
    }

    lateinit var parent:JoinActivity
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        parent = activity as JoinActivity

        parent.listener = object :JoinActivity.JoinListener {
            override fun didBackPress() {
                parent.listener = null
                parent.setCurrentPage(SmsAuthFragment.instance)
            }
        }

        user_info_next_button.setOnClickListener {
            parent.listener = null
            startActivity(Intent(parent, MainActivity::class.java))
            parent.finish()
        }
    }



    companion object {
        var instance = UserInfoFragment()
    }

}
