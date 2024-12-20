package com.example.bigproject.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class GalleryController(private val view: GalleryView, private val model: GalleryModel) {
    private val _setChosePhoto1 = MutableLiveData<Boolean?>()
    val setChosePhoto1: LiveData<Boolean?>
        get() = _setChosePhoto1
    private val _setChosePhoto2 = MutableLiveData<Boolean?>()
    val setChosePhoto2: LiveData<Boolean?>
        get() = _setChosePhoto2
    private val _finishMessage = MutableLiveData<Boolean>()
    val finishMessage: LiveData<Boolean>
        get() = _finishMessage
    private val _insertMessage = MutableLiveData<Boolean>()
    val insertMessage: LiveData<Boolean>
        get() = _insertMessage
    init {
        view.ChooseImage1.setOnClickListener {
            _setChosePhoto1.value=true
        }
        view.ChooseImage2.setOnClickListener {
            _setChosePhoto2.value=true
        }
        /**返回按键*/
        view.binding.back.setOnClickListener { _finishMessage.value=true}
        view.binding.saveButton.setOnClickListener { _insertMessage.value=true }
    }
}