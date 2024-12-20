package com.example.bigproject.newsrecycler

/** 列表每一项的数据*/
data class AddItem(
    /**id*/
    val id:Int?,
    /** 标题 */
    val title: String?,
    /**编著机构*/
    val author:String?,
    /** 摘要 */
    val description: String?,
    /** 内容 */
    val content: String?,
    /** 图像路径*/
    val  image_path1: ByteArray?,
    val  image_path2: ByteArray?
)