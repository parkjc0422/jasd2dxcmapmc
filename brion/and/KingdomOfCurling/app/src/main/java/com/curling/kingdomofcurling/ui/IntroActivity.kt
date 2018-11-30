package com.curling.kingdomofcurling.ui

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import com.curling.kingdomofcurling.R
import com.curling.kingdomofcurling.login.FacebookLoginManager
import com.curling.kingdomofcurling.login.NaverLoginManager
import kotlinx.android.synthetic.main.activity_intro.*
import android.content.Intent
import java.util.*
import kotlin.concurrent.timerTask


class IntroActivity : FragmentActivity() {
    lateinit var naverLogin:NaverLoginManager
    lateinit var facebookLoginManager: FacebookLoginManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        naverLogin = NaverLoginManager(buttonOAuthLoginImg,applicationContext)
        facebookLoginManager = FacebookLoginManager(applicationContext, facebookLogin)

        login_button_by_email.setOnClickListener {
            startActivity(Intent(this@IntroActivity, EmailLoginActivity::class.java))
            finish()
        }

        startLoginTimer()
    }

    var remainTick: Int = 4
    lateinit var timer:Timer
    fun startLoginTimer () {
        timer = Timer()

        timer.scheduleAtFixedRate(timerTask{

            if(remainTick > 0) {
                remainTick -= 1
                runOnUiThread {
                    remainTimerLogin.text = "${remainTick}초 회원가입"
                }
            } else {
                startActivity(Intent(this@IntroActivity, MainActivity::class.java))
                timer.cancel()
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
