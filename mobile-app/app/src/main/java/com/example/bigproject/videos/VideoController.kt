package com.example.bigproject.videos

import android.widget.SeekBar
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class VideoController(private val view: VideosView, private val model: VideoModel) {
    private val _preparedMessage = MutableLiveData<Boolean>()
    val preparedMessage: LiveData<Boolean>
        get() = _preparedMessage
    private val _finishMessage = MutableLiveData<Boolean>()
    val finishMessage: LiveData<Boolean>
        get() = _finishMessage
    init{
        view.videoView.requestFocus()
        /**自定义mediacontroller*/
        view.videoView?.setOnPreparedListener { mp ->
            mp.isLooping = true
            model.MAX_PROGRESS = view.videoView!!.duration
            view.mSeekBar.max= view.videoView!!.duration
            // 开始线程，更新进度条的刻度
            _preparedMessage.value=true
        }

        view.videoView.setOnCompletionListener {
            model.curProgress = 0
            view.mSeekBar.progress = 0
        }

        view.mSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    model.changeProgress(progress)
                    view.changeSeekBarValue()
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {
                    model.touchSeekBar()
                    view.SeekBar()
                    model.isSeekBarProgress=true
            }
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                view.videoView.seekTo(seekBar.progress)
                    model.startVideo()
                    view.SeekBar()
                model.isSeekBarProgress=false
            }
        })
        view.videoView.setOnClickListener{
            if(view.videoView.isPlaying==true)
            {
                model.pauseVideo()
                view.SeekBar()
            }
            else if(view.videoView.isPlaying==false)
            {
                model.startVideo()
                view.SeekBar()
            }
        }

        /**返回按键*/
        view.binding.back2.setOnClickListener {
            _finishMessage.value=true
        }
    }
}