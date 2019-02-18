package com.curling.kingdomofcurling.ui.fragment.login

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.curling.kingdomofcurling.R
import com.curling.kingdomofcurling.ui.EmailLoginActivity
import com.curling.kingdomofcurling.ui.IntroActivity
import com.curling.kingdomofcurling.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_email_login.*

class EmailLoginFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_email_login, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val parent = activity as EmailLoginActivity
        parent.listener = object : EmailLoginActivity.LoginTitleListener{
            override fun didPressedBack() {
                val parent = activity as EmailLoginActivity
                parent.startActivity(Intent(parent, IntroActivity::class.java))
                parent.finish()
            }
        }

        logonButtonEmail.setOnClickListener {
            val parent = activity as EmailLoginActivity
            startActivity(Intent(parent, MainActivity::class.java))
            parent.finish()
        }

        findPassword.setOnClickListener {
            parent.setCurrentPage(FindPasswordFragment.newInstance())
        }

        parent.setPageTitle(resources.getString(R.string.login_email_title))

        parent.listener = object :EmailLoginActivity.LoginTitleListener {
            override fun didPressedBack() {
                val parent = activity as EmailLoginActivity
                startActivity(Intent(parent, IntroActivity::class.java))
                parent.finish()
            }
        }
    }

    companion object {
        fun newInstance () :EmailLoginFragment {
            var instance = EmailLoginFragment ()
            return instance
        }
    }

}
