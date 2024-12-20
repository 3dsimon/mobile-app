package com.example.bigproject.Islogined

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.bigproject.database.AccountDBHelper
import com.example.bigproject.account.AccountItem
import com.example.bigproject.databinding.AccountLoginedBinding

class Islogined:  AppCompatActivity() {
    private var binding: AccountLoginedBinding?=null
    private var view: IsloginedView? = null
    private var model:IsloginedModel?=null
    private var controller:IsloginedController?=null
    var dbHelper: AccountDBHelper?=null
    var accountlist:List<AccountItem>?=null
    var listno:Int?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model= IsloginedModel()
        binding = AccountLoginedBinding.inflate(layoutInflater)
        view= IsloginedView(binding!!,model!!).apply {
            controller = IsloginedController(this, model!!)
        }
        setContentView(binding!!.root)
        dbHelper= AccountDBHelper(this)
        accountlist=dbHelper?.readvalues(this)
        listno=intent.getStringExtra("key")?.toInt()
        model?.setallcontent(accountlist!!,listno!!)
        model?.showmode()
        view?.ViewLoad()
        controller?.finishMessage?.observe(this, Observer { message -> if(message==true)finish() })
        model?.updateMessage?.observe(this, Observer { message -> if(message==true)updatevalues() })
    }

    fun updatevalues(){
        dbHelper?.updatevalues(
            accountlist!![listno!!].id,
            model?.my_name,
            model?.my_description,
            model?.my_content
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        view = null
        controller = null
        binding=null
        model=null
        dbHelper=null
        accountlist=null
        listno=null
    }
}