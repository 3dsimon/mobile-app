package com.example.bigproject

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import android.widget.FrameLayout
import android.widget.ImageView
import com.example.bigproject.temperature.ActivityTemperature

class loading : AppCompatActivity() {
    private var rootView: FrameLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loading)
        rootView = findViewById(R.id.root)
        startTimer()
        window.statusBarColor = Color.BLACK
    }

    /**加载动画*/
    private var rotateAnimLoading: ImageView? = null
    private var frameAnimLoading:ImageView? = null
    @SuppressLint("ResourceType")
    private fun showRotateLoading() {
        rotateAnimLoading = findViewById(R.id.iv_loading)

        val anim = AnimationUtils.loadAnimation(this, R.anim.rotate_anim)
        anim.interpolator = LinearInterpolator()
        rotateAnimLoading?.startAnimation(anim)
    }
    private fun showFrameAnimLoading() {
        frameAnimLoading = findViewById(R.id.iv_loading2)
        (frameAnimLoading?.background as? AnimationDrawable)?.start()
    }
    /**设置定时器*/
    private val handler = Handler()
    private val runnable = Runnable {
        // 代码将在 300 毫秒后被调用
        stopTimer()
        startActivity(Intent(this, ActivityTemperature::class.java))
        finish()
    }
    private fun startTimer() {//开始计时
        showRotateLoading()
        showFrameAnimLoading()
        handler.postDelayed(runnable, 300)
    }
    private fun stopTimer() {//中断计时
        handler.removeCallbacks(runnable)
    }
}