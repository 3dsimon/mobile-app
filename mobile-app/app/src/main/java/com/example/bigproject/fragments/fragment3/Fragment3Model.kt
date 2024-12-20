package com.example.bigproject.fragments.fragment3

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.bigproject.Islogined.Islogined
import com.example.bigproject.R
import com.example.bigproject.account.AccountActivity
import com.example.bigproject.account.AccountItem

class Fragment3Model {
     fun searchAccount(accountlist:ArrayList<AccountItem>,accountno:String): Int? {
        var i=0
        if(accountlist!=null) {
            while (i in 0 until accountlist!!.size) {
                if (accountno == accountlist!![i].account) {
                    return i
                }
                i += 1
            }
        }
         return null
    }

    fun checkAccount(pos:Int?,accountlist: ArrayList<AccountItem>){
        if(pos==null){noAccount()}
        else { setAccount(pos,accountlist)}
    }

    var user_name:String?=null
    var user_description:String?=null
    var user_image: Bitmap?=null
    var user_image_resource:Int?=null
    var text_fr3_visibility:Int?=null
    var visibility:Int?=null
    fun setAccount(pos:Int,accountlist:ArrayList<AccountItem>){
        user_name=accountlist!![pos].name
        user_description="userID:"+accountlist!![pos].account
        user_image=BitmapFactory.decodeByteArray(accountlist!![pos].image_path, 0, accountlist!![pos].image_path!!.size)
        user_image_resource=null
        text_fr3_visibility= View.INVISIBLE
        visibility= View.VISIBLE
    }

    fun noAccount(){
        user_name="用户"
        user_description="登录账户以享受更多内容"
        user_image_resource=R.mipmap.cat
        user_image=null
        text_fr3_visibility= View.VISIBLE
        visibility= View.INVISIBLE
    }
}