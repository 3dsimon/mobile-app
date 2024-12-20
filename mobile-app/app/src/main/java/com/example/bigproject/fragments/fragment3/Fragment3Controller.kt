package com.example.bigproject.fragments.fragment3

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


class Fragment3Controller(private val view: Fragment3View, private val model: Fragment3Model) {
    private val _setPhoto = MutableLiveData<Boolean?>()
    val setPhoto: LiveData<Boolean?>
        get() = _setPhoto
    private val _login_page_confirm = MutableLiveData<Boolean?>()
    val login_page_confirm: LiveData<Boolean?>
        get() = _login_page_confirm
    private val _logout = MutableLiveData<Boolean?>()
    val logout: LiveData<Boolean?>
        get() = _logout

    init{
        view.change_icon?.setOnClickListener(){
           _setPhoto.value=true
        }

        /**登出*/
        view.logout?.setOnClickListener(){
            _logout.value=true
            model?.noAccount()
        }

        view.account?.setOnClickListener(){
            _login_page_confirm.value=true
        }
    }
}