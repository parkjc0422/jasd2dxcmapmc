package com.curling.kingdomofcurling.ui.join


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.curling.kingdomofcurling.R

class DetailProvisionFragment : Fragment() {
    lateinit var parent:JoinActivity
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_provision, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        parent = activity as JoinActivity
        parent.setTitleText(resources.getString(R.string.provision_detail_title))

        parent.listener = object : JoinActivity.JoinListener {
            override fun didBackPress() {
                parent.setCurrentPage(JoinProvisionFragment.instance)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        parent.listener = null
    }

    companion object {
        var provision:Provision? = null
        var instance = DetailProvisionFragment()
    }

}
