package com.curling.kingdomofcurling.ui

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import com.curling.kingdomofcurling.R
import com.curling.kingdomofcurling.login.FacebookLoginManager
import com.curling.kingdomofcurling.login.NaverLoginManager
import kotlinx.android.synthetic.main.activity_intro.*
import android.content.Intent
import android.view.View
import com.curling.kingdomofcurling.ui.join.JoinActivity
import java.util.*
import kotlin.concurrent.timerTask


class IntroActivity : FragmentActivity() {

    companion object {
        var isFirstTime:Boolean = true
    }

    lateinit var naverLogin:NaverLoginManager
    lateinit var facebookLoginManager: FacebookLoginManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        naverLogin = NaverLoginManager(buttonOAuthLoginImg,applicationContext) {
            startActivity(Intent(this@IntroActivity, MainActivity::class.java))
            finish()
        }

        facebookLoginManager = FacebookLoginManager(applicationContext, facebookLogin) {
            startActivity(Intent(this@IntroActivity, MainActivity::class.java))
            finish()
        }

        login_button_by_email.setOnClickListener {
            startActivity(Intent(this@IntroActivity, EmailLoginActivity::class.java))
            finish()
        }
        if(isFirstTime) {
            isFirstTime = false
            startLoginTimer()
        } else {
            remainTimerLogin.visibility = View.GONE
        }

        buttonOAuthLoginImg_dummy.setOnClickListener {
            timer?.cancel()
            buttonOAuthLoginImg.performClick()
        }

        facebookLogin_dummy.setOnClickListener {
            timer?.cancel()
            facebookLogin.performClick()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        timer?.cancel()
    }

    var remainTick: Int = 4

    private var timer:Timer? = null
    fun startLoginTimer () {
        timer = Timer()

        timer?.scheduleAtFixedRate(timerTask{
            if(remainTick > 0) {
                remainTick -= 1
                runOnUiThread {
                    remainTimerLogin.text = "${remainTick}초 회원가입"
                }
            } else {
                startActivity(Intent(this@IntroActivity, JoinActivity::class.java))
                timer?.cancel()
                finish()
            }
        }, 0, 1000)
    }


    /**
     * for Facebook Login module
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        facebookLoginManager.registResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }
}
