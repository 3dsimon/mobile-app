package com.example.bigproject.temperature

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bigproject.R

class TempRecyclerViewHolder : RecyclerView.ViewHolder {

    var ivWeather: ImageView    /** 封面 */
        private set

    var tvTemperature:TextView    /** 标题 */
        private set


    constructor(view: View) : super(view) {
        ivWeather= view.findViewById(R.id.weather)
        tvTemperature= view.findViewById(R.id.tv_temperature)
    }
}