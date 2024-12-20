package com.example.bigproject.Islogined

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


@MainThread
class IsloginedController(private val view: IsloginedView, private val model: IsloginedModel) {
    private val _finishMessage = MutableLiveData<Boolean>()
    val finishMessage: LiveData<Boolean>
        get() = _finishMessage
    init {
        view.binding.editButton.setOnClickListener {
            model.ReadAllFromView(view.name.text.toString(),view.description.text.toString(),view.content.text.toString())
            model.changemode()
            view?.ViewLoad()
        }
        view.binding.myBack.setOnClickListener{
            _finishMessage.value=true
        }
    }
}