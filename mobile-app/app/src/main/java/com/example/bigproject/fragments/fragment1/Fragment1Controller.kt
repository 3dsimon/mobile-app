package com.example.bigproject.fragments.fragment1

import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView


class Fragment1Controller(private val view: Fragment1View, private val model: Fragment1Model) {
    private val _searchCall = MutableLiveData<String?>()
    val searchCall: LiveData<String?>
        get() = _searchCall
    init {
        /**滚动事件*/
        view.NewsRecycler?.addOnScrollListener(object : RecyclerView.OnScrollListener()
        {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int)
            {
                super.onScrolled(recyclerView, dx, dy)
                /** 获取 RecyclerView 的滚动位置*/
                val scrollY = view.NewsRecycler?.computeVerticalScrollOffset()
                /** 随滑动增加 RecyclerView 高度*/
                if (scrollY != null)
                {
                    /**上滑伸出*/
                    if (scrollY==0||scrollY>model.originscroll as Int)
                    {
                        if (view.dialog?.layoutParams?.height!! >= 300) //判断窗口大小是否正常
                        {
                            if (model.visibility == View.VISIBLE && view.dialog?.layoutParams?.height!! < 500) //判断组件是否隐藏
                            {
                                model.makeInivisible()
                                view.setVisibility()
                            }
                            view.dialog?.layoutParams?.height = view.dialog?.layoutParams?.height?.minus(10 * model.coefficient!!)//增加recyclerView高度
                            view.NewsRecycler.requestLayout()
                            model.originscroll=scrollY
                        }
                    }
                    /**下滑收回*/
                    if (view.dialog?.layoutParams?.height!! < model.originheight!! &&scrollY==0)
                    {
                        if(model.visibility == View.INVISIBLE)
                        {
                            model.makeVisible()
                            view.setVisibility()
                        }
                        view.dialog?.layoutParams?.height =model.originheight//减少recyclerView高度
                        view.NewsRecycler.requestLayout()
                        model.originscroll=0//重置滚动计数
                    }
                }
            }
        })

        view.search_edit.setOnEditorActionListener{ _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_GO || (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER)) {
                // 当键盘的 Enter 键按下时执行跳转操作
                _searchCall.value=view.search_edit?.text.toString()
                return@setOnEditorActionListener true
            }
            false
        }
        view.start_search?.setOnClickListener{
            _searchCall.value=view.search_edit?.text.toString()
        }
    }
}