package com.example.bigproject.temperature

import com.example.bigproject.R
import kotlin.random.Random

class TemperatureModel {
    fun createListInfo(): ArrayList<TempItem> {
        val list = ArrayList<TempItem>()
        /**资讯内容*/
        var i: Int = 1
        while (i in 1..20) {
            val random = Random.nextInt(1, 4)
            when (random) {
                1 -> list.add(TempItem(R.mipmap.sun, "18"))
                2 -> list.add(TempItem(R.mipmap.cloud24, "20"))
                3 -> list.add(TempItem(R.mipmap.cloudlightning24, "22"))
                4 -> list.add(TempItem(R.mipmap.cloudyday24, "24"))
            }
            i += 1
        }
        return list
    }
}