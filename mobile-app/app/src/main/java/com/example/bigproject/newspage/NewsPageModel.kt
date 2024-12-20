package com.example.bigproject.newspage

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.bigproject.newsrecycler.AddItem

class NewsPageModel {
    var title:String?=null
    var author:String?=null
    var description:String?=null
    var content:String?=null
    var image: Bitmap?=null

    fun inputvalue(addlist:ArrayList<AddItem>,position:Int){
        title=addlist.get(position).title
        author="newspage"+(position+1).toString()
        description=addlist.get(position).description
        content=addlist.get(position).content
        image=BitmapFactory.decodeByteArray(addlist[position].image_path1, 0, addlist[position].image_path1!!.size)
    }
}