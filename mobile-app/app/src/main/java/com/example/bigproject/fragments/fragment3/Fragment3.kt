package com.example.bigproject.fragments.fragment3

import android.Manifest
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.bigproject.Islogined.Islogined
import com.example.bigproject.R
import com.example.bigproject.account.AccountActivity
import com.example.bigproject.database.AccountDBHelper
import com.example.bigproject.account.AccountItem
import com.example.bigproject.databinding.Fragment3Binding

class Fragment3 : Fragment() {
    private var binding: Fragment3Binding?=null
    private var view: Fragment3View? = null
    private var model: Fragment3Model?=null
    private var controller: Fragment3Controller?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        model= Fragment3Model()
        binding = Fragment3Binding.inflate(layoutInflater)
        view= Fragment3View(binding!!,model!!).apply {
            controller = Fragment3Controller(this, model!!)
        }
        init()
        controller?.login_page_confirm?.observe(viewLifecycleOwner, Observer { message -> loginstate()})
        controller?.logout?.observe(viewLifecycleOwner, Observer { message -> logout()})
        controller?.setPhoto?.observe(viewLifecycleOwner, Observer { message -> setphoto()})
        return binding?.root
    }

    var accountlist:ArrayList<AccountItem>?=null
    var dbHelper: AccountDBHelper?=null
    var position:Int?=null

    fun init(){
        dbHelper= AccountDBHelper(requireContext())
        position=login()
        model?.checkAccount(position,accountlist!!)
        view?.setview()
    }

    override fun onResume() {
        super.onResume()
        init()
    }

    private fun login():Int?{
// 获取 SharedPreferences 对象
        val sharedPreferences = activity?.getSharedPreferences("account_login_now", MODE_PRIVATE)
// 读取数据
        val accountno = sharedPreferences?.getString("account", null)
// 如果 "account" 不存在，则返回 null 作为默认值
        accountlist=dbHelper?.readvalues(requireContext())
        if (accountno!=null)return model?.searchAccount(accountlist!!,accountno!!)
        return null
    }

    /**打开相册属于危险权限，先为他赋予权限，弹出对话框点击确定之后会调用onRequestPermissionsResult*/
    private fun setphoto() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf<String>(Manifest.permission.READ_EXTERNAL_STORAGE),
                123
            )
        } else {
        }
    }

    /**判定弹出登录界面&个人主页*/
    fun loginstate(){
        if (position!=null)accountpage(position!!)
        else if(position==null)loginpage()
    }

    fun savephoto(imageUri:Uri){
        dbHelper?.updatephoto(accountlist!![position!!].id,getBitmapFromUri(imageUri!!)!!)//更新头像
        Toast.makeText(activity, "头像更换成功", Toast.LENGTH_SHORT).show()
        position=login()
        if(position!=null)model?.setAccount(position!!,accountlist!!)
    }

    fun loginpage(){
        val intent = Intent(activity, AccountActivity::class.java)
        startActivity(intent)
    }

    fun accountpage(pos:Int){
        val intent = Intent(activity, Islogined::class.java)
        intent.putExtra("key", "${pos}") // 通过键值对的方式传递数据
        startActivity(intent)
    }

    fun logout(){
        val sharedPreferences = activity?.getSharedPreferences("account_login_now", Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        editor?.putString("account",null )
        editor?.apply()
        position=null
        view?.setview()
    }
    private fun getBitmapFromUri(uri: Uri): Bitmap?
    {
        val inputStream=activity?.contentResolver?.openInputStream(uri)
        return BitmapFactory.decodeStream(inputStream)
    }

    override fun onDestroy() {
        super.onDestroy()
        view = null
        controller = null
        binding=null
        model=null
        dbHelper=null
        accountlist=null
    }
}