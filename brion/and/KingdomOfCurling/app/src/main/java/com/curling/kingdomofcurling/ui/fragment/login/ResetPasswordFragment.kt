package com.curling.kingdomofcurling.ui.fragment.login


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.curling.kingdomofcurling.R
import com.curling.kingdomofcurling.ui.EmailLoginActivity
import kotlinx.android.synthetic.main.fragment_reset_password.*

class ResetPasswordFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_reset_password, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val parent = activity as EmailLoginActivity
        parent.setPageTitle(resources.getString(R.string.login_reset_password))

        parent.listener = object : EmailLoginActivity.LoginTitleListener {
            override fun didPressedBack() {
                parent.setCurrentPage(FindPasswordFragment.newInstance())
            }
        }
        btnResetPasswordNext.setOnClickListener {
            parent.setCurrentPage(EmailLoginFragment.newInstance())
        }
    }


    companion object {
        fun newInstance () :ResetPasswordFragment{
            var instance = ResetPasswordFragment()
            return instance
        }
    }

}
