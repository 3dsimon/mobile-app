package com.example.bigproject.web

import android.webkit.WebSettings
import android.webkit.WebView

class WebModel {
    fun setWebsite(web:WebView,search:String): WebView {
        // 声明 WebSettings子类
        val webSettings = web.getSettings()
        // 如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true)
        // 缩放操作
        webSettings.setSupportZoom(true)   // 支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true)  // 设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false)   // 隐藏原生的缩放控件
        // 其他细节操作
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK)  // 关闭webview中缓存
        webSettings.setAllowFileAccess(true)    // 设置可以访问文件
        webSettings.setDefaultTextEncodingName("utf-8")   // 设置编码格式

        if(search==null)web?.loadUrl("https://www.baidu.com")  // 加载百度首页
        else if(search!=null)web?.loadUrl("https://m.baidu.com/s?word=${search}")  // 加载百度结果页
        return web
    }
}