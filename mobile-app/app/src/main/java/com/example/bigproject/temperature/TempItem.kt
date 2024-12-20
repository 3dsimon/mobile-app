package com.example.bigproject.temperature

/** 列表每一项的数据*/
data class TempItem(
    /** 图片地址 */
    val WeatherUrl: Int?,
    /** 标题 */
    val temperature: String?,
)