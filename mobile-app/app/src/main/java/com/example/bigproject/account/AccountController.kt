package com.example.bigproject.account

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

@MainThread
class AccountController(private val view: AccountView, private val model: AccountModel) {
    private val _finishMessage = MutableLiveData<Boolean>()
    val finishMessage: LiveData<Boolean>
        get() = _finishMessage
    init {
        view.binding.registerButton.setOnClickListener {
            model.modeChange()
            view.AccountModeLoad()
        }
        view.binding.loginButton.setOnClickListener{model.save(view.name,view.account,view.password,view.password2)}
        view.binding.accountBack.setOnClickListener{
            _finishMessage.value=true
        }
    }

}