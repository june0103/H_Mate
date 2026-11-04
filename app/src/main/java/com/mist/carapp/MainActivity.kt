package com.mist.carapp

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.webkit.ConsoleMessage
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import android.window.OnBackInvokedCallback
import android.window.OnBackInvokedDispatcher
import androidx.webkit.WebViewAssetLoader
import org.json.JSONObject

/**
 * 게임은 WebView(assets/index.html 메뉴 → highway/ 하이웨이, highway/?mode=circuit 서킷)에서 돌고,
 * 실제 차량 신호를 window.car.set()으로 넣어 준다.
 */
class MainActivity : Activity() {

    private lateinit var web: WebView
    private lateinit var vehicleInput: VehicleInput
    private lateinit var vehicleControl: VehicleControl
    private lateinit var chagok: ChagokBridge

    // 페이지가 다 뜨기 전에 온 값은 버려지므로 최신값을 모아 두었다가 onPageFinished에서 한 번에 보낸다 (UI 스레드에서만 접근)
    private val latest = JSONObject()
    private var pageReady = false

    // 하이웨이는 ES 모듈과 fetch()로 에셋을 읽는데 file://에서는 막힌다 → https 가상 주소로 assets를 서빙
    private val assetLoader by lazy {
        WebViewAssetLoader.Builder()
            .addPathHandler("/assets/", WebViewAssetLoader.AssetsPathHandler(this))
            .build()
    }

    // 게임 화면에서 뒤로 가기 = 메뉴로 (API 33+는 OnBackInvokedCallback, 그 아래는 onBackPressed)
    private var backCallback: Any? = null

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 디버그 빌드만: chrome://inspect 로 WebView 콘솔·성능 확인
        if (applicationInfo.flags and android.content.pm.ApplicationInfo.FLAG_DEBUGGABLE != 0) {
            WebView.setWebContentsDebuggingEnabled(true)
        }
        web = WebView(this).apply {
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true                   // 최고 기록 저장
            settings.mediaPlaybackRequiresUserGesture = false   // 터치 없이도 엔진음
            webViewClient = object : WebViewClient() {
                override fun shouldInterceptRequest(view: WebView, request: WebResourceRequest): WebResourceResponse? =
                    assetLoader.shouldInterceptRequest(request.url)

                // 차곡 데모는 주행 최적화 화면(ChagokActivity)에서 연다
                override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
                    if (request.url.path?.endsWith("/chagok-app.html") != true) return false
                    startActivity(android.content.Intent(this@MainActivity, ChagokActivity::class.java))
                    return true
                }

                override fun onPageStarted(view: WebView, url: String, favicon: android.graphics.Bitmap?) {
                    pageReady = false
                }

                override fun onPageFinished(view: WebView, url: String) {
                    pageReady = true
                    if (latest.length() > 0) send(latest)
                }

                override fun doUpdateVisitedHistory(view: WebView, url: String, isReload: Boolean) {
                    updateBack()
                }
            }
            // 페이지 콘솔(에셋 로드 실패 등)을 logcat 'CarAppWeb' 태그로
            webChromeClient = object : WebChromeClient() {
                override fun onConsoleMessage(msg: ConsoleMessage): Boolean {
                    Log.println(
                        if (msg.messageLevel() == ConsoleMessage.MessageLevel.ERROR) Log.ERROR else Log.INFO,
                        "CarAppWeb", "${msg.message()} (${msg.sourceId()}:${msg.lineNumber()})",
                    )
                    return true
                }
            }
            // 차량 제어 화면(control.html)이 부르는 공조·앰비언트 브리지
            vehicleControl = VehicleControl(this@MainActivity) { msg ->
                runOnUiThread { evaluateJavascript("window.onCarControl && window.onCarControl($msg)", null) }
            }
            addJavascriptInterface(vehicleControl, "carControl")
            // 차곡 데이터 화면(chagok.html)이 부르는 연료·트립·문·착석·순정 내비 브리지
            chagok = ChagokBridge(this@MainActivity) { msg ->
                runOnUiThread { evaluateJavascript("window.onChagok && window.onChagok($msg)", null) }
            }
            addJavascriptInterface(chagok, "chagok")
            loadUrl("https://appassets.androidplatform.net/assets/index.html")
        }
        setContentView(web)

        vehicleInput = VehicleInput(this) { state -> push(state) }
    }

    override fun onStart() {
        super.onStart()
        vehicleInput.start()
    }

    override fun onStop() {
        vehicleInput.stop()
        super.onStop()
    }

    override fun onDestroy() {
        vehicleInput.release()
        vehicleControl.release()
        chagok.release()
        web.destroy()
        super.onDestroy()
    }

    @Deprecated("API 33 미만에서만 불린다")
    override fun onBackPressed() {
        if (web.canGoBack()) web.goBack() else super.onBackPressed()
    }

    private fun updateBack() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) return
        val wanted = web.canGoBack()
        if (wanted && backCallback == null) {
            val cb = OnBackInvokedCallback { web.goBack() }
            onBackInvokedDispatcher.registerOnBackInvokedCallback(OnBackInvokedDispatcher.PRIORITY_DEFAULT, cb)
            backCallback = cb
        } else if (!wanted && backCallback != null) {
            onBackInvokedDispatcher.unregisterOnBackInvokedCallback(backCallback as OnBackInvokedCallback)
            backCallback = null
        }
    }

    private fun push(state: JSONObject) = runOnUiThread {
        // 방향지시등은 변속 이벤트라 다시 보내면 기어가 한 번 더 바뀐다 → 모아 두지 않는다
        state.keys().forEach { key -> if (key != "signal") latest.put(key, state.get(key)) }
        if (pageReady) send(state)
    }

    private fun send(state: JSONObject) {
        web.evaluateJavascript("window.car && window.car.set($state)", null)
    }
}
