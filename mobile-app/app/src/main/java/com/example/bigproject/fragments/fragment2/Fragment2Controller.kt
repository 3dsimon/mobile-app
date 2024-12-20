package com.example.bigproject.fragments.fragment2

import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bigproject.R

class Fragment2Controller(private val view: Fragment2View, private val model: Fragment2Model) {
    private val _searchCall = MutableLiveData<String?>()
    val searchCall: LiveData<String?>
        get() = _searchCall
    /**在Fragment中写一个回调接口*/
    init{
            view.search_web?.setOnEditorActionListener { _, actionId, event ->
                    if (actionId == EditorInfo.IME_ACTION_GO || (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER)) {
                        // 当键盘的 Enter 键按下时执行跳转操作
                        _searchCall.value=view.search_web.text.toString()
                        return@setOnEditorActionListener true
                    }
                    false
                }
            view.start_search_web?.setOnClickListener() {
                _searchCall.value=view.search_web.text.toString()
            }
        }
    }