package com.example.bigproject.temperature

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bigproject.R

class TempRecyclerAdapter : RecyclerView.Adapter<TempRecyclerViewHolder> {

    /** 保存数据的列表 */
    private val tempList: ArrayList<TempItem>

    constructor(demoList: ArrayList<TempItem>) {
        this. tempList= demoList
    }

    /** 创建视口载体 */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TempRecyclerViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.temperature_item, parent, false)
        return TempRecyclerViewHolder(view)
    }

    /** 绑定数据 */
    override fun onBindViewHolder(holder: TempRecyclerViewHolder, position: Int) {
        val itemBean =  tempList[position]

        itemBean.WeatherUrl?.let {
            holder.ivWeather?.setImageResource(it)
        }

        itemBean.temperature?.let {
            holder.tvTemperature.text = it
        }
    }


    override fun getItemCount(): Int {
        return tempList.size
    }
}