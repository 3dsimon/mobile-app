package com.example.bigproject.videos

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import com.example.bigproject.R
import com.example.bigproject.databinding.VideoPlayerBinding

class VideoViewActivity:  AppCompatActivity() {
    private var binding: VideoPlayerBinding?=null
    private var view: VideosView? = null
    private var model: VideoModel?=null
    private var controller: VideoController?=null
    private var handler:Handler?=null
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model= VideoModel()
        binding = VideoPlayerBinding.inflate(layoutInflater)
        view= VideosView(binding!!,model!!).apply{
            controller = VideoController(this, model!!)
        }
        handler=Handler()
        setContentView(binding!!.root)
        init()
        controller?.preparedMessage?.observe(this, Observer { message -> if(message==true)prepared()})
        controller?.finishMessage?.observe(this, Observer { message -> if(message==true)finish() })
    }
    fun init(){
        model?.videoPath = "android.resource://" + packageName + "/" + R.raw.outer_wilds_ost
        view?.viewinit()
    }
    // 播放过程，自动更新seekbar的进度
    private val runnable: Runnable = object:Runnable {
        override fun run() {
            if (view?.videoView?.isPlaying == true) {
                model?.seekBarRun(view!!.videoView.currentPosition)
                view?.BarChange()
            }
            handler?.postDelayed(this, 100)
        }
    }

    private fun prepared(){
        handler?.postDelayed(runnable, 0)
        model?.startVideo()
        view?.SeekBar()
    }

    override fun onDestroy() {
        super.onDestroy()
        handler?.removeCallbacks(runnable)
        view = null
        controller = null
        binding=null
        model=null
    }
}