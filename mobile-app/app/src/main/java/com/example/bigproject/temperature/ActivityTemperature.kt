package com.example.bigproject.temperature

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bigproject.R
import com.example.bigproject.databinding.ActivityTemperatureBinding
import com.example.bigproject.databinding.NewsPageBinding
import com.example.bigproject.newspage.NewsPageController
import com.example.bigproject.newspage.NewsPageModel
import com.example.bigproject.newspage.NewsPageView
import kotlin.random.Random


class ActivityTemperature : AppCompatActivity() {
    private var binding: ActivityTemperatureBinding?=null
    private var view: TemperatureView? = null
    private var model: TemperatureModel?=null
    private var controller: TemperatureController?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model= TemperatureModel()
        binding = ActivityTemperatureBinding.inflate(layoutInflater)
        view= TemperatureView(binding!!,model!!).apply{
            controller = TemperatureController(this, model!!)
        }
        setContentView(binding!!.root)
        TempListCreate()
        transparentStatusBar(this)
    }

    /**天气列表*/
    fun TempListCreate() {
        /** newsRecycleView 实例 */
        var temprecyclerview: RecyclerView? = binding?.rvTemp
        /** newsRecycleView 的适配器  */
        var tempadapter: TempRecyclerAdapter? = TempRecyclerAdapter(model?.createListInfo()!!)
        /**添加RecycleView*/
        temprecyclerview?.adapter = tempadapter
        temprecyclerview?.layoutManager = LinearLayoutManager(this)
        (temprecyclerview?.layoutManager as LinearLayoutManager).orientation=LinearLayoutManager.HORIZONTAL
    }

    /**顶部状态栏透明*/
    fun transparentStatusBar(activity: Activity) {
        transparentStatusBar(activity.window)
    }
    fun transparentStatusBar(window: Window) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) return
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            val option = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            val vis = window.decorView.systemUiVisibility
            window.decorView.systemUiVisibility = option or vis
            window.statusBarColor = Color.TRANSPARENT
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        view = null
        controller = null
        binding=null
        model=null
    }
}