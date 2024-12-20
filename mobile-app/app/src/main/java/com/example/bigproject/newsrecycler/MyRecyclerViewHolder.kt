package com.example.bigproject.newsrecycler

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bigproject.R

class MyRecyclerViewHolder : RecyclerView.ViewHolder {

    var ivCover: ImageView

    var tvTitle:TextView    /** 标题 */
        private set

    var tvAuthor: TextView    /** 内容 */
        private set

    var tvType: TextView    /** 标题*/
    private set

    constructor(view: View) : super(view) {
        ivCover = view.findViewById(R.id.cover)
        tvTitle = view.findViewById(R.id.tv_title)
        tvAuthor = view.findViewById(R.id.tv_author)
        tvType=view.findViewById(R.id.tv_type)
    }
}