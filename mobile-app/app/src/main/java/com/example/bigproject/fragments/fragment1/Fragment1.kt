package com.example.bigproject.fragments.fragment1

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bigproject.database.NewsDBHelper
import com.example.bigproject.databinding.Fragment1Binding
import com.example.bigproject.newspage.NewsActivity
import com.example.bigproject.newsrecycler.AddItem
import com.example.bigproject.newsrecycler.MyRecyclerAdapter


class Fragment1: Fragment(),MyRecyclerAdapter.ItemClickListener {
    private var binding: Fragment1Binding?=null
    private var view: Fragment1View? = null
    private var model:Fragment1Model?=null
    private var controller:Fragment1Controller?=null
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        model= Fragment1Model()
        binding = Fragment1Binding.inflate(layoutInflater)
        model?.originheight=binding?.imagedialog?.layoutParams?.height
        model?.coefficient=resources.displayMetrics.density.toInt()//获取设备屏幕密度
        /**天气渐显动画*/
        view?.TemperatureAlphaAnim()
        dbinit()
        view= Fragment1View(binding!!,model!!).apply {
            controller = Fragment1Controller(this, model!!)
        }
        controller?.searchCall?.observe(viewLifecycleOwner, Observer { message ->  mDataCallBack?.searchWeb(message) })
        return binding!!.root
    }

    private var addlist:ArrayList<AddItem>?=null
    var dbHelper: NewsDBHelper?= null
    /**新闻列表*/
    @RequiresApi(Build.VERSION_CODES.P)
    private fun NewsListCreate(){
        /** newsRecycleView 的适配器  */
        var adapter: MyRecyclerAdapter? = null
        /**添加RecycleView*/
        adapter = MyRecyclerAdapter(model?.createListInfo(addlist!!)!!,this)//将addlist的内容添加进newslist中
        binding?.rvFr1?.adapter = adapter
        binding?.rvFr1?.layoutManager = LinearLayoutManager(activity)
    }

    override fun onItemClick(position: Int) {
        // 处理子项点击事件，启动新的 Activity
        val intent = Intent(activity, NewsActivity::class.java)
        intent.putExtra("key", "${position}") // 通过键值对的方式传递数据
        startActivity(intent)
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onDeleteClick(position: Int) {
        dbHelper?.dbDelete(addlist!![position].id!!)
        dbinit()
        //删除数据
    }

    /**在Fragment中onAttach方法中得到Activity实现好的实例化接口对象*/
    var mDataCallBack: DataCallBack?=null
    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        mDataCallBack = activity as DataCallBack
    }
    /**用接口对象进行数据传递*/
    interface DataCallBack {
        fun searchWeb(text:String?)
    }

    @RequiresApi(Build.VERSION_CODES.P)
    fun dbinit(){
        dbHelper=NewsDBHelper(requireContext())
        addlist=dbHelper?.readvalues(requireContext())
        model?.checkempty(addlist!!)
        view?.setVisibility()
        if(addlist!=null)NewsListCreate()
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onResume() {
        super.onResume()
        view?.setOriginHeight()
        dbinit()
        // 在 onResume() 方法中检测从其他 Activity 返回的情况
        // 刷新数据、更新界面
    }

    override fun onDestroy() {
        super.onDestroy()
        view = null
        controller = null
        binding=null
        model=null
        dbHelper=null
        addlist=null
    }
}
