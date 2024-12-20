package com.example.bigproject.videos

import android.view.View.INVISIBLE
import android.view.View.VISIBLE

class VideoModel {
        var isTimeVisible:Int?=null
        var TimeSet:String?=null
        var videoPath:String?=null
         var MAX_PROGRESS = 0
         var curProgress:Int?= null
        var PauseVisibility:Int?=INVISIBLE
        var TimeVisibility:Int?=INVISIBLE
        var isSeekBarProgress:Boolean?=false
        var PlayState:Boolean?=null
    fun changeProgress(progress:Int){
        isTimeVisible= VISIBLE
        var promin="${progress/60/1000}"
        var prosec="${progress/1000%60}"
        var maxpromin="${MAX_PROGRESS/60/1000}"
        var maxprosec="${MAX_PROGRESS/1000%60}"
        if(progress/60/1000<10){ promin="0${progress/60/1000}"}
        if(progress/1000%60<10){prosec="0${progress/1000%60}"}
        if(MAX_PROGRESS/60/1000<10){maxpromin="0${MAX_PROGRESS/60/1000}"}
        if(MAX_PROGRESS/1000%60<10){maxprosec="0${MAX_PROGRESS/1000%60}"}
        TimeSet="${promin}:${prosec}/${maxpromin}:${maxprosec}"
    }

    fun pauseVideo(){
        PauseVisibility= VISIBLE
        PlayState=false
    }
    fun touchSeekBar(){
        PauseVisibility= VISIBLE
        TimeVisibility=VISIBLE
        PlayState=false
    }
    fun startVideo(){
        PauseVisibility= INVISIBLE
        TimeVisibility= INVISIBLE
        PlayState=true
    }
    fun seekBarRun(progress:Int){
        if (!isSeekBarProgress!!) {
            curProgress=progress
        }
    }
}