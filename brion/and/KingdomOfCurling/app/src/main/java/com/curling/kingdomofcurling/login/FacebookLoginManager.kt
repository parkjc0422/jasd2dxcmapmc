package com.curling.kingdomofcurling.login

import android.content.Context
import android.content.Intent
import com.facebook.CallbackManager
import com.facebook.FacebookButtonBase
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.FacebookCallback
import com.facebook.login.widget.LoginButton
import com.facebook.login.LoginManager
import com.facebook.AccessToken


class FacebookLoginManager (val mContext: Context, val facebookButtonBase: LoginButton){
    companion object {
        val ApplicationId = "323542258468432"
    }
    var callbackManager:CallbackManager
    init {
        callbackManager = CallbackManager.Factory.create()

        facebookButtonBase.setReadPermissions("email")

        // Callback registration
        facebookButtonBase.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                // App code
            }

            override fun onCancel() {
                // App code
            }

            override fun onError(exception: FacebookException) {
                // App code
            }
        })


        callbackManager = CallbackManager.Factory.create()

        LoginManager.getInstance().registerCallback(callbackManager,
                object : FacebookCallback<LoginResult> {
                    override fun onSuccess(loginResult: LoginResult) {
                        // App code
                    }

                    override fun onCancel() {
                        // App code
                    }

                    override fun onError(exception: FacebookException) {
                        // App code
                    }
                })

    }



    fun isLogin():Boolean {
        val accessToken = AccessToken.getCurrentAccessToken()
        return accessToken != null && !accessToken.isExpired
    }

    fun registResult (requestCode:Int, resultCode:Int, data: Intent) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }
}