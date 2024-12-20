package com.example.bigproject.fragments.fragment1

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.BitmapFactory
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.bigproject.R
import com.example.bigproject.newsrecycler.AddItem
import com.example.bigproject.newsrecycler.NewsItem
import kotlin.random.Random

class Fragment1Model {
    var coefficient:Int?=null
     var originheight:Int?=null//初始高度
     var originscroll:Int?=null//判断手势是否在上滑
    fun setanimator(parentLayout:ViewGroup):ObjectAnimator{
        var animator = ObjectAnimator.ofFloat(parentLayout, "alpha", 0f, 1f)
        animator.duration = 5000  // 设置动画持续时间为 5000 毫秒
        animator.repeatCount = ValueAnimator.INFINITE  // 设置动画无限重复
        animator.repeatMode = ValueAnimator.REVERSE  // 设置动画重复模式为反向
        return animator
    }

    fun createListInfo(addlist:ArrayList<AddItem>): ArrayList<NewsItem> {
        val list = ArrayList<NewsItem>()
        /**资讯内容*/
        var i: Int = 0
        if (addlist?.size!! >0) {
            while (i in 0 until addlist?.size!!) {
                val bit= BitmapFactory.decodeByteArray(addlist!![i].image_path2, 0, addlist!![i].image_path2!!.size)
                list.add(
                    NewsItem(
                        bit,
                        addlist!![i].title,
                        addlist!![i].author,
                        random_type()
                    )
                )
                i += 1
            }
        }
        return list
    }
    private fun random_type():String{
        var random = Random.nextInt(1, 4)
        when (random) {
            1 ->return "热点"
            2 ->return "时事"
            3 ->return "要闻"
            4 ->return "最新"
        }
        return ""
    }

    var visibility:Int?=View.VISIBLE
    var resource:Int?=R.color.transparent
    var emptyVisibility:Int?=View.INVISIBLE
    fun makeVisible(){
        visibility= View.VISIBLE
        resource= R.color.transparent//输入框透明
    }
    fun makeInivisible(){
        visibility=View.INVISIBLE
        resource=R.drawable.frame//输入框加线
    }
    fun checkempty(addlist: ArrayList<AddItem>){
        if (addlist?.size!! ==0)emptyVisibility= View.VISIBLE
        else if(addlist?.size!! !=0)emptyVisibility= View.INVISIBLE
    }
}