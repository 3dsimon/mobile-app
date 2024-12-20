package com.example.bigproject.account

import android.view.View
import android.widget.EditText
import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bigproject.R

class AccountModel():ViewModel() {
    private var IsRegister: Boolean = false
    interface DataListener {
        fun login(list:ArrayList<AccountItem>,position: Int)
        fun writedatabase(name:String,account:String,password:String)
    }
    @MainThread
    fun modeChange() {
        IsRegister = !IsRegister
        if (IsRegister == true) RegisterMode()
        else if (IsRegister == false) LoginMode()
    }

    var visibility = View.INVISIBLE
    var login_button_text = "登录"
    var background = R.drawable.buttonframe
    fun RegisterMode() {
        visibility = View.VISIBLE
        login_button_text = "确认"
        background = R.drawable.pressed_buttonframe
    }

    fun LoginMode() {
        visibility = View.INVISIBLE
        login_button_text = "登录"
        background = R.drawable.buttonframe
    }
    /**传回给activity用于发Toast*/
    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String>
        get() = _toastMessage
    private val _finishMessage = MutableLiveData<Boolean>()
    val finishMessage: LiveData<Boolean>
        get() = _finishMessage

    var accountlist:ArrayList<AccountItem>?=null
    @MainThread
    fun save(name: EditText?, account: EditText?, password: EditText?, password2: EditText?) {
        if (IsRegister) {
            if (name?.text == null) {
                _toastMessage.value = "名称不能为空"
                return
            }
                if (account?.text == null) {
                    _toastMessage.value = "账号不能为空"
                    return
                }
                var i = 0
                if (accountlist != null) {
                    while (i in 0 until accountlist!!.size) {
                        if (account?.text.toString() == accountlist!![i].account) {
                            _toastMessage.value = "该账号已存在"
                            return
                        }
                        i += 1
                    }
                }
                if (password?.text == null) {
                    _toastMessage.value = "密码不能为空"
                    return
                }
                if (password?.text.toString() != password2?.text.toString()) {
                    _toastMessage.value = "密码与确认密码不同，请重新输入"
                    return
                }
                writedatabase(
                    name?.text.toString(),
                    account?.text.toString(),
                    password?.text.toString(),
                )
            }  else if(!IsRegister) {
                var i=0
                while( i in 0 until accountlist!!.size){
                    if (account?.text.toString()==accountlist!![i].account&&password?.text.toString()==accountlist!![i].password){login(i);return}
                    i+=1
                }
                _toastMessage.value ="账号登录失败，账号或密码不存在"
            }
        }

    private var dataListener: DataListener? = null
    fun setDataListener(listener: DataListener) {
        dataListener = listener
    }
    /**发送数据到activity*/
    fun login(position: Int)
    {
        dataListener?.login(accountlist!!,position)
    }
    fun writedatabase(name:String,account:String,password:String)
    {
        dataListener?.writedatabase(name,account,password)
    }
}