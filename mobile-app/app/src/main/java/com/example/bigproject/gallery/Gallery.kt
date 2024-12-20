package com.example.bigproject.gallery

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.example.bigproject.R
import com.example.bigproject.database.NewsDBHelper
import com.example.bigproject.databinding.AddphotoBinding
import com.example.bigproject.databinding.WebviewBinding
import com.example.bigproject.web.WebController
import com.example.bigproject.web.WebModel
import com.example.bigproject.web.WebViews

class Gallery :  AppCompatActivity() {
    private var binding: AddphotoBinding?=null
    private var view: GalleryView? = null
    private var model: GalleryModel?=null
    private var controller: GalleryController?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model= GalleryModel()
        binding = AddphotoBinding.inflate(layoutInflater)
        view= GalleryView(binding!!,model!!).apply{
            controller = GalleryController(this, model!!)
        }
        setContentView(binding!!.root)
        controller?.finishMessage?.observe(this, Observer { message -> finish()})
        controller?.setChosePhoto1?.observe(this, Observer { message -> setChose_photo1()})
        controller?.setChosePhoto2?.observe(this, Observer { message -> setChose_photo2()})
        controller?.insertMessage?.observe(this, Observer { message -> dbInsert()})
        // 检查读取外部存储权限
        window.statusBarColor = Color.WHITE
    }


    /**打开相册属于危险权限，先为他赋予权限，弹出对话框点击确定之后会调用onRequestPermissionsResult*/
    private fun setChose_photo1() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf<String>(Manifest.permission.READ_EXTERNAL_STORAGE),
                1
            )
        } else {
        }
    }

    private fun setChose_photo2() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf<String>(Manifest.permission.READ_EXTERNAL_STORAGE),
                2
            )
        } else {
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        val result=model?.requestcode(requestCode,grantResults)
        when (result){
            0->Toast.makeText(this, "你还没有统一访问相册的权限", Toast.LENGTH_SHORT).show()
            1->openAlbum1()
            2->openAlbum2()
        }
    }

    /**读取缩放图*/
    private fun openAlbum1() {
        val intent = Intent(Intent.ACTION_PICK)
        //选择相册 intent.type(“audio/*”); //选择音频 intent.type(“video/*”); //选择视频
        intent.type = "image/*"
        startActivityForResult(intent,1);
    }
    /**读取头图*/
    private fun openAlbum2() {
        val intent = Intent(Intent.ACTION_PICK)
        //选择相册 intent.type(“audio/*”); //选择音频 intent.type(“video/*”); //选择视频
        intent.type = "image/*"
        startActivityForResult(intent,2);
    }
    /**写入照片*/
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && data != null) {
            if(requestCode == 1)
            {
                val imageUri = data.data
                if (imageUri != null) {
                    model?.bitmap1=getBitmapFromUri(imageUri)
                }
                view?.setphoto1()
            }
            if(requestCode == 2)
            {
                val imageUri = data.data
                if (imageUri != null) {
                    model?.bitmap2=getBitmapFromUri(imageUri)
                }
                view?.setphoto2()
            }
        }
    }
    private fun getBitmapFromUri(uri: Uri): Bitmap?
    {
        val inputStream=contentResolver.openInputStream(uri)
        return BitmapFactory.decodeStream(inputStream)
    }

    private fun dbInsert() {
        // 访问数据库，先实例化 MyDBHelper 的子类
        val dbhelper = NewsDBHelper(this)
        // 以写入模式获取数据存储库
        // 插入数据
        val result=model?.dbinsert(dbhelper,view?.newstitle?.text.toString(),view?.newsauthor?.text.toString(),view?.newsdescription?.text.toString(), view?.newscontent?.text.toString())
        when (result){
            0->{
                Toast.makeText(this, "请选择缩略图", Toast.LENGTH_SHORT).show()
                return
            }
            1->{
                Toast.makeText(this, "请选择头图", Toast.LENGTH_SHORT).show()
                return
            }
            2->{
                Toast.makeText(this, "已写入数据库", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

    }

}
