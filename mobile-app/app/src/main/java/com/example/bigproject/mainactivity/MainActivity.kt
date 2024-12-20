package com.example.bigproject.mainactivity

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.bigproject.MyFragmentPagerAdapter
import com.example.bigproject.R
import com.example.bigproject.databinding.ActivityMainBinding
import com.example.bigproject.databinding.AddphotoBinding
import com.example.bigproject.gallery.Gallery
import com.example.bigproject.fragments.fragment1.Fragment1
import com.example.bigproject.fragments.fragment2.Fragment2
import com.example.bigproject.fragments.fragment3.Fragment3
import com.example.bigproject.gallery.GalleryController
import com.example.bigproject.gallery.GalleryModel
import com.example.bigproject.gallery.GalleryView
import com.example.bigproject.loading
import com.example.bigproject.web.WebviewActivity
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity(), Fragment1.DataCallBack, Fragment2.DataCallBack2 {
    private var binding: ActivityMainBinding?=null
    private var view: MainView? = null
    private var model: MainModel?=null
    private var controller: MainController?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model= MainModel()
        binding = ActivityMainBinding.inflate(layoutInflater)
        view= MainView(binding!!,model!!).apply{
            controller = MainController(this, model!!)
        }
        setContentView(binding!!.root)
        init()
        window.statusBarColor = Color.rgb(50,50,255)
    }

    private fun init() {
        val adapter = MyFragmentPagerAdapter(this)
        view?.viewPager2?.setAdapter(adapter)
        //防止内存泄露
        view?.viewPager2?.setOffscreenPageLimit(2) //为1时，第三个碎片会出现内存泄漏。
        fragment3=adapter.Fragment3
    }

    fun jump(view: View) {
        startActivity(Intent(this, loading::class.java))
    }

    fun gallery(view: View) {
        startActivity(Intent(this, Gallery::class.java))
    }
    var fragment3: Fragment3?=null
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            123 -> if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openAlbum()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == AppCompatActivity.RESULT_OK && data != null) {
            if(requestCode==456)
            {
                val imageUri = data.data
                if (imageUri != null) {
                    fragment3?.savephoto(imageUri)
                }
            }
        }
    }

    private fun openAlbum() {
        val intent = Intent(Intent.ACTION_PICK)
        //选择相册 intent.type(“audio/*”); //选择音频 intent.type(“video/*”); //选择视频
        intent.type = "image/*"
        startActivityForResult(intent,456);
    }

    override fun searchWeb(text:String?) {
        // 处理子项点击事件，启动新的 Activity
        val intent = Intent(this, WebviewActivity::class.java)
        intent.putExtra("search", text) // 通过键值对的方式传递数据
        startActivity(intent)
    }
}
