package com.example.bigproject.newsrecycler

import android.graphics.Bitmap

/** 列表每一项的数据*/
data class NewsItem(
    /** 图片 */
    val coverUrl: Bitmap?,
    /** 标题 */
    val title: String?,
    /** 编著机构 */
    val author: String?,
    /** 类别 */
    val type: String?
)