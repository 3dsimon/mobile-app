package com.example.bigproject.Islogined

import androidx.annotation.MainThread
import com.example.bigproject.databinding.AccountLoginedBinding

@MainThread
class IsloginedView (val binding: AccountLoginedBinding,val model:IsloginedModel){
    val name=binding.editMyName
    val description=binding.editMyDescription
    val content=binding.editMyContent
@MainThread
    fun ViewLoad() {
    name.setText(model.my_name)
    description.setText(model.my_description)
    content.setText(model.my_content)
    name.inputType=model.inputType!!
    description.inputType=model.inputType!!
    content.inputType=model.inputType!!
    name.setBackgroundResource(model.bgResource!!)
    description.setBackgroundResource(model.bgResource!!)
    content.setBackgroundResource(model.bgResource!!)
    description.isSingleLine = false
    content.isSingleLine = false
    description.minLines=5
    content.minLines=30
    binding.editButton.setBackgroundResource(model.button_bgResource!!)
    binding.editButton.setText(model.button_text)
    }
}