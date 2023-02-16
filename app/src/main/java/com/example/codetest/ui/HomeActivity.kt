package com.example.codetest.ui

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.Settings
import android.view.KeyEvent
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.codetest.R
import com.example.codetest.utils.EspressoIdlingResource
import com.example.codetest.utils.References
import com.google.android.material.snackbar.Snackbar

class HomeActivity : AppCompatActivity() {

    private lateinit var mWebView: WebView
    private lateinit var dialog: Dialog
    private lateinit var alertDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        mWebView = findViewById(R.id.webView)

        createLoadingDialogue()
        createConnectionAlertDialogue()

        if (References.checkNetworkStatus(this))
            configureWebView()
        else {
            alertDialog.show()
        }
    }

    private fun createLoadingDialogue() {
        dialog = Dialog(this)
        dialog.apply {
            setContentView(R.layout.loading_dialogue)
            setCancelable(false)
        }
    }

    private fun createConnectionAlertDialogue() {
        alertDialog = AlertDialog.Builder(this)
            .setCancelable(false)
            .setTitle(R.string.connection_problem)
            .setMessage(R.string.connection_message)
            .setPositiveButton(R.string.connect) { _, _ ->
                val intent = Intent(Settings.ACTION_WIFI_SETTINGS)
                startActivity(intent)
                finish()
            }
            .setNegativeButton(R.string.exit) { _, _ ->
                finishAndRemoveTask()
            }
            .create()
    }

    private fun configureWebView() {

        val mWebViewClient = object : WebViewClient() {
            // Prevents this app from switching to a default web browser app
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                return request?.url?.host?.equals(References.EVENT_PAGE_LINK) == true
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                EspressoIdlingResource.increment()
                dialog.show()
                if (url?.equals(References.EVENT_PAGE_LINK) == true) {
                    val intent = Intent(applicationContext, ProductActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                dialog.hide()
                EspressoIdlingResource.decrement()
                super.onPageFinished(view, url)
            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                val snackBar =
                    Snackbar.make(view!!, error?.description!!, Snackbar.LENGTH_SHORT)
                snackBar.setAction(R.string.retry) {
                    view.loadUrl(References.WEB_LINK)
                }.show()
                super.onReceivedError(view, request, error)
            }
        }

        mWebView.apply {
            webViewClient = mWebViewClient
            settings.javaScriptEnabled = true   // To support web content developed by javascript
            loadUrl(References.WEB_LINK)
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (mWebView.canGoBack() && keyCode == KeyEvent.KEYCODE_BACK) {
            mWebView.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}