package com.example.bigproject.gallery

import android.content.pm.PackageManager
import android.graphics.Bitmap
import com.example.bigproject.database.NewsDBHelper

class GalleryModel {
    var bitmap1: Bitmap?=null
    var bitmap2: Bitmap?=null
    fun requestcode(requestCode:Int,grantResults:IntArray):Int?{
        when (requestCode) {
            1 -> if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                return 1
            } else { return 0}

            2->if(grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                return 2
            }else {}
        }
        return null
    }

    fun dbinsert(dbhelper:NewsDBHelper,newstitle:String, newsauthor:String, newsdescription:String, newscontent:String,):Int{
        if(bitmap1==null)
        {
            return 0
        }
        if(bitmap2==null)
        {
            return 1
        }
        dbhelper.putvalues(
            newstitle,
            newsauthor,
            newsdescription,
            newscontent,
            bitmap1!!,
            bitmap2!!,
        )
        return 2
    }
}