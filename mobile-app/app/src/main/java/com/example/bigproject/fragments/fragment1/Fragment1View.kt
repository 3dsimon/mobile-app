package com.example.bigproject.fragments.fragment1

import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bigproject.databinding.Fragment1Binding

class Fragment1View(val binding:Fragment1Binding, val model: Fragment1Model) {
    val NewsRecycler:RecyclerView=binding.rvFr1
    val dialog:ImageView=binding.imagedialog
    var search_edit:EditText=binding.searchEdit
    val start_search:TextView=binding.startSearch
    /**天气渐显事件*/
    fun TemperatureAlphaAnim() {
        val animator=model.setanimator(binding.constraintLayoutTemp)
        animator.start()  // 启动动画
    }
    fun setVisibility(){
        binding.constraintLayoutTemp.visibility=model.visibility!!
        binding.Title.visibility= model.visibility!!
        binding.addgallery.visibility= model.visibility!!
        binding.imagedialog.visibility= model.visibility!!
        binding.empty.visibility=model.emptyVisibility!!
        search_edit?.setBackgroundResource(model.resource!!)
    }
    fun setOriginHeight(){
        binding?.imagedialog?.layoutParams?.height=model.originheight
    }
}