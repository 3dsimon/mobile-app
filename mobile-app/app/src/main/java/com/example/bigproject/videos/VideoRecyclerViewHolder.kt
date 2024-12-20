package com.example.bigproject.videos

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bigproject.R

class VideoRecyclerViewHolder : RecyclerView.ViewHolder {

    var ivVideo: ImageView
    /** 封面 */
    private set

    var tvname: TextView
    /** 标题 */
    private set


            constructor(view: View) : super(view) {
        ivVideo= view.findViewById(R.id.video)
        tvname= view.findViewById(R.id.video_name)
    }
}