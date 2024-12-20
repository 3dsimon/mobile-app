package com.example.bigproject

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.bigproject.fragments.fragment1.Fragment1
import com.example.bigproject.fragments.fragment2.Fragment2
import com.example.bigproject.fragments.fragment3.Fragment3


class MyFragmentPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    var Fragment1: Fragment1 = Fragment1()
    var Fragment2: Fragment2 = Fragment2()
    var Fragment3: Fragment3 = Fragment3()

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return Fragment1
            1 -> return Fragment2
            2 -> return Fragment3
        }
        return Fragment()
    }
}