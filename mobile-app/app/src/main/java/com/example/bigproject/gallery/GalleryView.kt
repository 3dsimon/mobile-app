package com.example.bigproject.gallery

import com.example.bigproject.databinding.AddphotoBinding

class GalleryView(val binding: AddphotoBinding, val model: GalleryModel) {
    val ChooseImage1=binding.smallphoto
    val ChooseImage2=binding.headphoto
    val newstitle=binding.editText
    val newsauthor=binding.editAuthor
    val newsdescription=binding.editText3
    val newscontent=binding.editText4
    fun setphoto1(){
        ChooseImage1.setImageBitmap(model.bitmap1)
    }
    fun setphoto2(){
        ChooseImage2.setImageBitmap(model.bitmap2)
    }
}