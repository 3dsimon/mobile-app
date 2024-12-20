package com.example.bigproject.fragments.fragment2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bigproject.R
import com.example.bigproject.databinding.Fragment1Binding
import com.example.bigproject.databinding.Fragment2Binding
import com.example.bigproject.fragments.fragment1.Fragment1Controller
import com.example.bigproject.fragments.fragment1.Fragment1Model
import com.example.bigproject.fragments.fragment1.Fragment1View
import com.example.bigproject.videos.VideoItem
import com.example.bigproject.videos.VideoRecyclerAdapter
import com.example.bigproject.videos.VideoViewActivity

class Fragment2 : Fragment(), VideoRecyclerAdapter.ItemClickListener {
    private var binding: Fragment2Binding?=null
    private var view: Fragment2View? = null
    private var model: Fragment2Model?=null
    private var controller: Fragment2Controller?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        model= Fragment2Model()
        binding = Fragment2Binding.inflate(layoutInflater)
        view= Fragment2View(binding!!,model!!).apply {
            controller = Fragment2Controller(this, model!!)
        }
        controller?.searchCall?.observe(viewLifecycleOwner, Observer { message ->  mDataCallBack?.searchWeb(message) })
        VideoListCreate()
        return binding?.root
    }

    /**视频列表创建*/
    private fun VideoListCreate() {
        /** newsRecycleView 的适配器  */
        var videoadapter: VideoRecyclerAdapter? = null
        /**添加RecycleView*/
        videoadapter = VideoRecyclerAdapter(model?.createListInfo()!!,this)
        binding?.rvFr2?.adapter = videoadapter
        binding?.rvFr2?.layoutManager = LinearLayoutManager(activity)
    }

    override fun onItemClick(position: Int) {
        // 处理子项点击事件，启动新的 Activity
        val intent = Intent(activity, VideoViewActivity::class.java)
        intent.putExtra("key", "${position}") // 通过键值对的方式传递数据
        startActivity(intent)
    }

    /**在Fragment中onAttach方法中得到Activity实现好的实例化接口对象*/
    var mDataCallBack: DataCallBack2?=null
    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        mDataCallBack = activity as DataCallBack2
    }
    /**用接口对象进行数据传递*/
    interface DataCallBack2 {
        fun searchWeb(text:String?)
    }

    override fun onDestroy() {
        super.onDestroy()
        view = null
        controller = null
        binding=null
        model=null
    }
}