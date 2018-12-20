package com.curling.kingdomofcurling.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import com.curling.kingdomofcurling.R
import com.curling.kingdomofcurling.ui.fragment.login.EmailLoginFragment
import kotlinx.android.synthetic.main.activity_email_login.*

class EmailLoginActivity : FragmentActivity() {

    var listener: LoginTitleListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_login)

        setCurrentPage(EmailLoginFragment.newInstance())

        login_email_title_left_button.setOnClickListener {
            listener?.didPressedBack()
        }

    }

    override fun onBackPressed() {
        listener?.didPressedBack()
    }

    fun setCurrentPage (frament: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.loginEmailFrame, frament)
        transaction.commit()
    }

    interface LoginTitleListener {
        fun didPressedBack()
    }

}
