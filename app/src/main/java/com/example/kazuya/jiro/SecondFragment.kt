package com.example.kazuya.jiro

import android.os.Bundle
import android.view.ViewGroup
import android.view.LayoutInflater
import android.support.v4.app.Fragment
import android.view.View
import android.webkit.CookieManager
import android.webkit.WebView
import android.webkit.WebViewClient

class SecondFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        print("hello")
        // TODO: do something
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_second, null)
        val myWebView = view.findViewById<View>(R.id.ankiWebView) as WebView
        myWebView.settings.javaScriptEnabled = true
        CookieManager.getInstance().setAcceptCookie(true)
        myWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                // return true //Indicates WebView to NOT load the url;
                return false //Allow WebView to load url
            }
        }
        myWebView.loadUrl("https://ankiweb.net/decks/")
        return view
    }
}