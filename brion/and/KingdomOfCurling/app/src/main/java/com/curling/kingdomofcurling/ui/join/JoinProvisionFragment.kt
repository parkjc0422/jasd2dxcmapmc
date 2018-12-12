package com.curling.kingdomofcurling.ui.join

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.curling.kingdomofcurling.R
import com.curling.kingdomofcurling.ui.EmailLoginActivity
import kotlinx.android.synthetic.main.fragment_join_provision.*

class JoinProvisionFragment : Fragment() {
    lateinit var parent: JoinActivity
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_join_provision, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        parent = activity as JoinActivity
        parent.setTitleText(resources.getString(R.string.provision_title))

        initAction()

        provision_checkbox_1.isChecked = parent.joinInfo.provision1
        provision_checkbox_2.isChecked = parent.joinInfo.provision2
    }

    override fun onDestroy() {
        super.onDestroy()
        parent.listener = null
    }

    fun initAction () {

        parent.listener = object : JoinActivity.JoinListener{
            override fun didBackPress() {

            }
        }

        provision_see_1.setOnClickListener {
            DetailProvisionFragment.provision = Provision().apply {
                this.title = resources.getString(R.string.provision_1)
            }

            parent.setCurrentPage(DetailProvisionFragment.instance)
        }

        provision_see_2.setOnClickListener {
            DetailProvisionFragment.provision = Provision().apply {
                this.title = resources.getString(R.string.provision_2)
            }

            parent.setCurrentPage(DetailProvisionFragment.instance)
        }

        provision_checkbox_1.setOnCheckedChangeListener { compoundButton, b ->
            provision_checkbox_all.isChecked = b && provision_checkbox_2.isChecked
            parent.joinInfo.provision1 = b
        }

        provision_checkbox_2.setOnCheckedChangeListener { compoundButton, b ->
            provision_checkbox_all.isChecked = provision_checkbox_1.isChecked && b
            parent.joinInfo.provision2 = b
        }

        provision_checkbox_all.setOnCheckedChangeListener { compoundButton, b ->
            if(b) {
                provision_checkbox_1.isChecked = b
                provision_checkbox_2.isChecked = b
                provision_agree_button.isEnabled = b
            } else {
                if(provision_checkbox_1.isChecked && provision_checkbox_2.isChecked) {
                    provision_checkbox_1.isChecked = b
                    provision_checkbox_2.isChecked = b
                }
                provision_agree_button.isEnabled = b
            }

        }


        provision_agree_button.setOnClickListener {
            Log.d("ProvisionFragment", "click")
        }
    }



    companion object {
        var instance : JoinProvisionFragment = JoinProvisionFragment()
    }
}
