package com.example.myapplication.test.incident

import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_incident_detail2.*


/**
 * Created by Kumuthini.N on 28-06-2020
 */
class IncidentDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_incident_detail2)
        val description = intent.extras?.getString("Description")
        title = intent.extras?.getString("Title")
        bindView(description)
    }

    private fun bindView(description: String?) {
        webView.settings.loadWithOverviewMode = true
        webView.settings.useWideViewPort = true

        webView.webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest) =
                false

            override fun onPageFinished(view: WebView, url: String) {
            }
        }

        webView.loadDataWithBaseURL(null, description, "text/HTML", "UTF-8", null)

    }
}
