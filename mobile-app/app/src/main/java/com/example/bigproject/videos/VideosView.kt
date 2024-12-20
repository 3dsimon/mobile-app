package com.example.bigproject.videos

import android.net.Uri
import com.example.bigproject.databinding.VideoPlayerBinding

class VideosView(val binding: VideoPlayerBinding, val model: VideoModel) {
    val mSeekBar = binding.seekbar
    val videoView = binding.mvVideo
    fun viewinit(){
        videoView?.setVideoURI(Uri.parse(model.videoPath))
    }
    fun SeekBar() {
        binding.videoTime.visibility=model.TimeVisibility!!
        binding.videoPause.visibility=model.PauseVisibility!!
        if(model.PlayState==true)videoView.start()
        else if(model.PlayState==false)videoView.pause()
    }
    fun changeSeekBarValue(){
        binding.videoTime.text=model.TimeSet
    }
    fun BarChange(){
        mSeekBar.progress=model.curProgress!!
    }
}