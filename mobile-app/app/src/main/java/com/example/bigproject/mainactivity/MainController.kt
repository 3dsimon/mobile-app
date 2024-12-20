package com.example.bigproject.mainactivity

import androidx.viewpager2.widget.ViewPager2
import com.example.bigproject.R
import com.google.android.material.navigation.NavigationBarView

class MainController(private val view: MainView, private val model: MainModel) {
    val mOnItemSelectedListener =
        NavigationBarView.OnItemSelectedListener { item -> //底部导航栏被点击后
            model.menuItem = item
            //viewPager2.setCurrentItem(item.getOrder());
            if (item.itemId == R.id.one) {
                view.viewPager2.currentItem = 0
                return@OnItemSelectedListener true
            } else if (item.itemId == R.id.two) {
                view.viewPager2.currentItem = 1
                return@OnItemSelectedListener true
            } else if (item.itemId == R.id.three) {
                view.viewPager2.currentItem = 2
                return@OnItemSelectedListener true
            }
            false
        }
    init{
        view.mNavigationView.setOnItemSelectedListener(mOnItemSelectedListener)
        view.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(p: Int) {
                model.menuItem = view.mNavigationView.menu.getItem(p)
                model.menuItem!!.isChecked = true
            }
        })
    }
}