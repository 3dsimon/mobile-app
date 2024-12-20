package com.example.bigproject.newspage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class NewsPageController(private val view: NewsPageView, private val model: NewsPageModel) {
    private val _finishMessage = MutableLiveData<Boolean>()
    val finishMessage: LiveData<Boolean>
        get() = _finishMessage
    init{
        view.binding.back3.setOnClickListener { _finishMessage.value=true }
    }
}