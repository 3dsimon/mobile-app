package com.example.bigproject.fragments.fragment2

import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bigproject.databinding.Fragment2Binding

class Fragment2View(val binding: Fragment2Binding, val model: Fragment2Model) {
    var videorecyclerview: RecyclerView=binding.rvFr2
    val search_web:EditText=binding.searchWeb
    val start_search_web:TextView=binding.startSearchWeb
}