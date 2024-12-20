package com.example.bigproject.fragments.fragment3

import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.bigproject.databinding.Fragment3Binding

class Fragment3View(val binding: Fragment3Binding, val model: Fragment3Model) {
    var change_icon: TextView?=binding.changeIcon
    var change_icon_icon: ImageView?=binding.changeIconIcon
    var logout: TextView?=binding.logout
    var logout_icon: ImageView?=binding.logoutIcon
    var account: ConstraintLayout?=binding.nameLayout

    fun setview(){
    if(model.user_image!=null)binding.userImage.setImageBitmap(model.user_image)
        if(model.user_image_resource!=null)binding.userImage.setImageResource(model.user_image_resource!!)
    binding.userName.text=model.user_name
    binding.userDescription.text=model.user_description
    binding.changeIcon.visibility=model.visibility!!
    binding.changeIconIcon.visibility=model.visibility!!
    binding.logout.visibility=model.visibility!!
    binding.logoutIcon.visibility=model.visibility!!
    binding.textFr3.visibility=model.text_fr3_visibility!!
}
}