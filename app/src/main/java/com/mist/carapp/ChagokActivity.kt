package com.mist.carapp

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.webkit.ConsoleMessage
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.webkit.WebViewAssetLoader

/**
 * 차곡 데모(assets/chagok-app.html) 전용 화면. 게임(MainActivity)과 달리 주행 최적화(distractionOptimized)로 선언해
 * 주행 중에도 시스템이 가리지 않는다. 정차 전용 화면은 페이지가 주행 규제 신호를 받아 직접 숨기고 3줄 카드만 보인다.
 */
class ChagokActivity : Activity() {

    private lateinit var web: WebView
    private lateinit var chagok: ChagokBridge

    private val assetLoader by lazy {
        WebViewAssetLoader.Builder()
            .addPathHandler("/assets/", WebViewAssetLoader.AssetsPathHandler(this))
            .build()
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        web = WebView(this).apply {
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true // 여행 기록·설정 저장
            webViewClient = object : WebViewClient() {
                override fun shouldInterceptRequest(view: WebView, request: WebResourceRequest): WebResourceResponse? =
                    assetLoader.shouldInterceptRequest(request.url)

                // "← 메뉴"는 게임 메뉴(MainActivity)로 돌아가기
                override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
                    if (request.url.path?.endsWith("/index.html") != true) return false
                    finish()
                    return true
                }
            }
            webChromeClient = object : WebChromeClient() {
                override fun onConsoleMessage(msg: ConsoleMessage): Boolean {
                    Log.println(
                        if (msg.messageLevel() == ConsoleMessage.MessageLevel.ERROR) Log.ERROR else Log.INFO,
                        "CarAppWeb", "${msg.message()} (${msg.sourceId()}:${msg.lineNumber()})",
                    )
                    return true
                }
            }
            chagok = ChagokBridge(this@ChagokActivity) { msg ->
                runOnUiThread { evaluateJavascript("window.onChagok && window.onChagok($msg)", null) }
            }
            addJavascriptInterface(chagok, "chagok")
            loadUrl("https://appassets.androidplatform.net/assets/chagok-app.html")
        }
        setContentView(web)
    }

    override fun onDestroy() {
        chagok.release()
        web.destroy()
        super.onDestroy()
    }

    @Deprecated("API 33 미만에서만 불린다")
    override fun onBackPressed() {
        if (web.canGoBack()) web.goBack() else super.onBackPressed()
    }
}
