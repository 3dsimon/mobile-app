package com.example.bigproject.mainactivity

import com.example.bigproject.databinding.ActivityMainBinding

class MainView(val binding: ActivityMainBinding, val model: MainModel) {
    var viewPager2 = binding.viewPager
    var mNavigationView = binding.rgTabBar
}