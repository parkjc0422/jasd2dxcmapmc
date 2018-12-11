package com.curling.kingdomofcurling.ui.game

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.webkit.WebSettings
import android.webkit.WebViewClient
import com.curling.kingdomofcurling.R
import kotlinx.android.synthetic.main.activity_curling_game.*
import android.os.Build



class CurlingGameActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_curling_game)


        loadGame()
    }

    fun loadGame (){
        gameWebContent.webViewClient = WebViewClient()

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            if(!gameWebContent.settings.layoutAlgorithm.equals(WebSettings.LayoutAlgorithm.SINGLE_COLUMN))
                gameWebContent.settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        } else {
            if(!gameWebContent.settings.loadWithOverviewMode) gameWebContent.settings.loadWithOverviewMode = true
            if (!gameWebContent.settings.useWideViewPort) gameWebContent.settings.useWideViewPort = true
        }
        gameWebContent.settings.javaScriptEnabled = true

        gameWebContent.loadUrl("http://13.124.230.228:11010/part/4/")

    }

}
