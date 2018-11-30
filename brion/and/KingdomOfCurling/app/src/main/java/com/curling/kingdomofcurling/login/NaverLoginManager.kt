package com.curling.kingdomofcurling.login

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import android.widget.Toast
import com.nhn.android.naverlogin.OAuthLogin
import com.nhn.android.naverlogin.OAuthLoginHandler
import com.nhn.android.naverlogin.data.OAuthErrorCode
import com.nhn.android.naverlogin.data.OAuthLoginPreferenceManager
import com.nhn.android.naverlogin.ui.view.OAuthLoginButton


class NaverLoginManager (val button:OAuthLoginButton, val mContext: Context){
    val TAG = "NaverLoginManager"

    companion object {
        var mOAuthLoginInstance: OAuthLogin = OAuthLogin.getInstance()
        var naverObject: NaverLoginObject? = null
        /**
         * client 정보를 넣어준다.
         */
        private val OAUTH_CLIENT_ID = "6ff5GS4N0pZwlhMI1Jv9"
        private val OAUTH_CLIENT_SECRET = "nlxtHcAlV1"
        private val OAUTH_CLIENT_NAME = "curling_chek"
    }
    init {
        mOAuthLoginInstance = OAuthLogin.getInstance()

        mOAuthLoginInstance.showDevelopersLog(true)
        mOAuthLoginInstance.init(mContext, OAUTH_CLIENT_ID, OAUTH_CLIENT_SECRET, OAUTH_CLIENT_NAME)

        button.setOAuthLoginHandler(object : OAuthLoginHandler() {
            override fun run(success: Boolean) {
                if (success) {
                    naverObject = NaverLoginObject()
                    naverObject?.accessToken =mOAuthLoginInstance.getAccessToken(mContext)
                    naverObject?.refreshToken = mOAuthLoginInstance.getRefreshToken(mContext)
                    naverObject?.expiresAt = mOAuthLoginInstance.getExpiresAt(mContext)
                    naverObject?.tokenType = mOAuthLoginInstance.getTokenType(mContext)


                } else {
                    val errorCode = mOAuthLoginInstance.getLastErrorCode(mContext).getCode()
                    val errorDesc = mOAuthLoginInstance.getLastErrorDesc(mContext)
                    Toast.makeText(mContext, "errorCode:$errorCode, errorDesc:$errorDesc", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    fun logout() {
        val prefMng = OAuthLoginPreferenceManager(mContext)
        prefMng.accessToken = ""
        prefMng.refreshToken = ""
        prefMng.lastErrorCode = OAuthErrorCode.NONE
        prefMng.lastErrorDesc = ""
    }

    fun deleteToken (listener:()->Unit){
        DeleteTokenTask(listener).execute()
    }

    fun requestApiTask (listener: (String) -> Unit) {
        RequestApiTask(listener).execute()
    }

    fun refreshToken (listener: () -> Unit) {
        RefreshTokenTask(listener)
    }

    private inner class DeleteTokenTask (val listener: () -> Unit): AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg params: Void): Void? {
            val isSuccessDeleteToken = mOAuthLoginInstance.logoutAndDeleteToken(mContext)

            if (!isSuccessDeleteToken) {
                // 서버에서 token 삭제에 실패했어도 클라이언트에 있는 token 은 삭제되어 로그아웃된 상태이다
                // 실패했어도 클라이언트 상에 token 정보가 없기 때문에 추가적으로 해줄 수 있는 것은 없음
                Log.d(TAG, "errorCode:" + mOAuthLoginInstance.getLastErrorCode(mContext))
                Log.d(TAG, "errorDesc:" + mOAuthLoginInstance.getLastErrorDesc(mContext))
            }

            return null
        }

        override fun onPostExecute(v: Void) {
            listener()
        }
    }


    private inner class RequestApiTask(val listener: (String) -> Unit) : AsyncTask<Void, Void, String>() {
        override fun onPreExecute() {}

        override fun doInBackground(vararg params: Void): String {
            val url = "https://openapi.naver.com/v1/nid/me"
            val at = mOAuthLoginInstance.getAccessToken(mContext)
            return mOAuthLoginInstance.requestApi(mContext, at, url)
        }

        override fun onPostExecute(content: String) {
            listener(content)
        }
    }

    private inner class RefreshTokenTask(val listener: () -> Unit) : AsyncTask<Void, Void, String>() {
        override fun doInBackground(vararg params: Void): String {
            return mOAuthLoginInstance.refreshAccessToken(mContext)
        }

        override fun onPostExecute(res: String) {
            listener()
        }
    }

    class NaverLoginObject {
        lateinit var accessToken: String
        lateinit var refreshToken: String
        lateinit var tokenType: String
        var expiresAt: Long = 0
    }
}