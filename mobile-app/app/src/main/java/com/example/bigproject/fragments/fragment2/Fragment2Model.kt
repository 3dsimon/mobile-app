package com.example.bigproject.fragments.fragment2

import com.example.bigproject.R
import com.example.bigproject.videos.VideoItem

class Fragment2Model {

    fun createListInfo(): ArrayList<VideoItem> {
        val list = ArrayList<VideoItem>()
        /**资讯内容*/
        var i: Int = 1
        while (i in 1..30) {
            list.add(
                VideoItem(
                    R.mipmap.scene1,
                    "新闻${i}",
                )
            )
            i += 1
            list.add(
                VideoItem(
                    R.mipmap.scene2,
                    "新闻${i}",
                )
            )
            i += 1
            list.add(
                VideoItem(
                    R.mipmap.scene3,
                    "新闻${i}",
                )
            )
            i += 1
            list.add(
                VideoItem(
                    R.mipmap.scene4,
                    "新闻${i}",
                )
            )
            i += 1
        }
        return list
    }

}