package com.example.bigproject.Islogined

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bigproject.R
import com.example.bigproject.account.AccountItem

class IsloginedModel {
    var my_name: String?=null
    var my_description: String?=null
    var my_content: String?=null
    var editmode:Boolean=false

    fun changemode(){
        editmode=!editmode
        if(editmode==true)editmode()
        else if(editmode==false)showmode()
    }

    fun setallcontent( accountlist:List<AccountItem>,listno:Int) {
        my_name=accountlist!![listno!!].name
        my_description=accountlist!![listno!!].description
        my_content=accountlist!![listno!!].content
    }

    fun ReadAllFromView(name:String?,description:String?,content:String?){
        my_name=name
        my_description=description
        my_content=content
    }
    var inputType:Int?=null
    var bgResource:Int?=null
    var button_bgResource:Int?=null
    var button_text:String?=null


    fun editmode(){
        inputType=android.text.InputType.TYPE_CLASS_TEXT
        bgResource= R.drawable.frame
        button_bgResource=R.drawable.pressed_buttonframe
        button_text="确认"
    }

    private val _updateMessage = MutableLiveData<Boolean>()
    val updateMessage: LiveData<Boolean>
        get() = _updateMessage

    fun showmode(){
        inputType=android.text.InputType.TYPE_NULL
        bgResource=R.color.transparent
        button_bgResource=R.drawable.buttonframe
        button_text="编辑"
        _updateMessage.value=true
    }
}