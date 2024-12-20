package com.example.bigproject.web

import android.app.Activity
import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.webkit.*
import com.example.bigproject.R
import com.example.bigproject.databinding.VideoPlayerBinding
import com.example.bigproject.databinding.WebviewBinding
import com.example.bigproject.videos.VideoController
import com.example.bigproject.videos.VideoModel
import com.example.bigproject.videos.VideosView

class WebviewActivity : Activity() {

    val TAG = "WebviewActivity"
    private var binding: WebviewBinding?=null
    private var view: WebViews? = null
    private var model: WebModel?=null
    private var controller: WebController?=null
    // var webView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model= WebModel()
        binding = WebviewBinding.inflate(layoutInflater)
        view= WebViews(binding!!,model!!).apply{
            controller = WebController(this, model!!)
        }
        setContentView(binding!!.root)
        val webView = model?.setWebsite(binding?.webview1!!,intent.getStringExtra("search")!!)

        webView!!.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                // 设置不用系统浏览器打开,直接显示在当前Webview
                if (url.startsWith("http")) {
                    view.loadUrl(url)
                }
                return true
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                // 设定加载开始的操作
                // 我们可以设定一个loading的页面，告诉用户网页在加载中。
            }

            override fun onPageCommitVisible(view: WebView?, url: String?) {
                super.onPageCommitVisible(view, url)
                // 设定可视阶段的操作
                // webview可视阶段调用，指后端返回数据，webview开始渲染时回调，当前一般还是白屏状态。
            }

            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                // 设定加载结束的操作
                // 这个时候的页面已经加载完成，核心的JS也已经运行，可以关闭loading条
            }

            override fun doUpdateVisitedHistory(view: WebView?, url: String?, isReload: Boolean) {
                super.doUpdateVisitedHistory(view, url, isReload)
                // webview历史变更的回调，告诉webview历史记录更新。
                Log.i(TAG, "doUpdateVisitedHistory :" + url)
            }

            override fun onReceivedError(view: WebView, request: WebResourceRequest, error: WebResourceError) {
                super.onReceivedError(view, request, error)
            }

            override fun onReceivedHttpError(view: WebView, request: WebResourceRequest, errorResponse: WebResourceResponse) {
                super.onReceivedHttpError(view, request, errorResponse)
            }

            override fun onReceivedSslError(view: WebView, handler: SslErrorHandler, error: SslError) {
                super.onReceivedSslError(view, handler, error)
            }
        }

        webView!!.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, progress: Int) {
                super.onProgressChanged(view, progress)
            }

            override fun onReceivedTitle(view: WebView?, title: String) {
                super.onReceivedTitle(view, title)
            }
        }
    }

    // webview后退
    fun goBack(view: View) {
        val webView = findViewById<WebView>(R.id.webview1)
        webView?.goBack()
        // 可以先通过canGoBack()判断，不能后退的时候，改成退出Activity
    }

    // webview前进
    fun goForward(view: View) {
        val webView = findViewById<WebView>(R.id.webview1)
        webView?.goForward()
    }
}