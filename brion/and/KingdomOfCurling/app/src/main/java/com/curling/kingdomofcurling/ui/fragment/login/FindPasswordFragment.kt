package com.curling.kingdomofcurling.ui.fragment.login


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.curling.kingdomofcurling.R
import com.curling.kingdomofcurling.ui.EmailLoginActivity
import kotlinx.android.synthetic.main.fragment_find_password.*


class FindPasswordFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_find_password, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val parent = activity as EmailLoginActivity
        parent.setPageTitle(resources.getString(R.string.login_password_find))
        parent.listener = object : EmailLoginActivity.LoginTitleListener {
            override fun didPressedBack() {
                parent.setCurrentPage(EmailLoginFragment.newInstance())
            }
        }

        btnFindPasswordNext.setOnClickListener {
            parent.setCurrentPage(ResetPasswordFragment.newInstance())
        }
    }




    companion object {
        fun newInstance () :FindPasswordFragment{
            var instance = FindPasswordFragment()
            return instance
        }
    }
}
