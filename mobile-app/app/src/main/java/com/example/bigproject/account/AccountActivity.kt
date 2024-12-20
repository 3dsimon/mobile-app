package com.example.bigproject.account

import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.bigproject.R
import com.example.bigproject.databinding.AccountLoginBinding
import com.example.bigproject.account.AccountModel.DataListener
import com.example.bigproject.database.AccountDBHelper

class AccountActivity:AppCompatActivity(), DataListener {
    private var controller: AccountController? = null
    private var view: AccountView? = null
    private var model:AccountModel?=null
    private var binding:AccountLoginBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model= AccountModel()
        binding = AccountLoginBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        Init()
    }

    /**初始化视口、触发及模型事件*/
    private fun Init(){
        view= AccountView(binding!!,model!!).apply {
            controller = AccountController(this, model!!)
       }
        view?.AccountModeLoad()
        model?.toastMessage?.observe(this, Observer { message -> showToast(message) })
        controller?.finishMessage?.observe(this, Observer { message -> if(message==true)finish() })
        model?.finishMessage?.observe(this, Observer { message -> if(message==true)finish() })
        model?.setDataListener(this)
        val dbHelper= AccountDBHelper(this)
        model?.accountlist=dbHelper?.readvalues(this)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun login(list:ArrayList<AccountItem>,position:Int) {
// 获取 SharedPreferences 对象
        val sharedPreferences = getSharedPreferences("account_login_now",
            AppCompatActivity.MODE_PRIVATE
        )
// 获取 SharedPreferences 的编辑器
        val editor = sharedPreferences.edit()
// 存储数据
        editor.putString("account",list!![position].account )
        editor.apply() // 提交数据
        showToast("账号登录成功!")
        finish()
    }

    override fun writedatabase(name: String, account: String, password: String) {
        val dbHelper= AccountDBHelper(this)
        dbHelper?.putvalues(
            name,
            account,
            password,
            BitmapFactory.decodeResource(
                resources,
                R.mipmap.cat
            ),//加载图片资源标识符
            null,
            null
        )
        showToast("账号注册成功!")
        model?.modeChange()
        model?.accountlist=dbHelper?.readvalues(this)
        model?.save(null,view?.account,view?.password,null)
    }

    override fun onDestroy() {
        super.onDestroy()
        view = null
        controller = null
        binding=null
        model=null
    }
}