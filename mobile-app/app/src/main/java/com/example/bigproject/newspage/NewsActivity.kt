package com.example.bigproject.newspage

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.example.bigproject.database.NewsDBHelper
import com.example.bigproject.databinding.NewsPageBinding


class NewsActivity:AppCompatActivity() {
    private var binding: NewsPageBinding?=null
    private var view: NewsPageView? = null
    private var model: NewsPageModel?=null
    private var controller:NewsPageController?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model= NewsPageModel()
        binding = NewsPageBinding.inflate(layoutInflater)
        view= NewsPageView(binding!!,model!!).apply{
            controller = NewsPageController(this, model!!)
        }
        setContentView(binding!!.root)
        window.statusBarColor = Color.WHITE
        init()
        /**返回按键*/
        controller?.finishMessage?.observe(this, Observer { message -> if(message==true)finish() })
    }

    fun init(){
        setChose_photo()
        val position=intent.getStringExtra("key")
        val dbhelper=NewsDBHelper(this)
        val addlist=dbhelper.readvalues(this)
        model?.inputvalue(addlist,position!!.toInt())
        view?.setView()
    }
    /**打开相册属于危险权限，先为他赋予权限，弹出对话框点击确定之后会调用onRequestPermissionsResult*/
    private fun setChose_photo() {
        if (ContextCompat.checkSelfPermission(
                this,//MainActivity回应权限请求事件
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf<String>(Manifest.permission.READ_EXTERNAL_STORAGE),
                1
            )
        } else {
            Toast.makeText(this, "你还没有统一访问相册的权限", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            } else {
                Toast.makeText(this, "统一访问相册的权限申请失败", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        view = null
        controller = null
        binding=null
        model=null
    }
}