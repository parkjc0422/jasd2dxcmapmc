package com.curling.kingdomofcurling.ui.join


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.curling.kingdomofcurling.R
import kotlinx.android.synthetic.main.fragment_sms_auth.*

class SmsAuthFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sms_auth, container, false)
    }

    lateinit var parent :JoinActivity
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        parent = activity as JoinActivity

        parent.listener = object :JoinActivity.JoinListener {
            override fun didBackPress() {
                parent.setCurrentPage(JoinProvisionFragment.instance)
            }
        }


        send_sms_auth_number.setOnClickListener {
            sms_auth_number.setText("123456")
            sms_auth_next_button.isEnabled = true
        }

        sms_auth_next_button.setOnClickListener {
            parent.setCurrentPage(UserInfoFragment.instance)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        parent.listener = null
    }

    companion object {
        var instance = SmsAuthFragment()
    }

}
