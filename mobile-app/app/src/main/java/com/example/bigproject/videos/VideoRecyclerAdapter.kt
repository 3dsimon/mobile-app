package com.example.bigproject.videos

import android.graphics.Outline
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.bigproject.R
import com.example.bigproject.newsrecycler.NewsItem

class VideoRecyclerAdapter  : RecyclerView.Adapter<VideoRecyclerViewHolder> {

    /** 保存数据的列表 */
    private val VideoList: ArrayList<VideoItem>
    private var itemClickListener:ItemClickListener?=null
    interface ItemClickListener {
        fun onItemClick(position: Int)
    }
    constructor(demoList: ArrayList<VideoItem>, itemClickListener: ItemClickListener) {
        this.VideoList= demoList
        this.itemClickListener=itemClickListener
    }
        var view:View?=null
    /** 创建视口载体 */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoRecyclerViewHolder {
        view= LayoutInflater.from(parent.context).inflate(R.layout.video_item, parent, false)
        return VideoRecyclerViewHolder(view as View)
    }

    /** 绑定数据 */
    override fun onBindViewHolder(holder: VideoRecyclerViewHolder, position: Int) {
        val itemBean =  VideoList[position]

        itemBean.VideoItem?.let {
            holder.ivVideo?.setImageResource(it)
            outlinebundary(holder.ivVideo)//按形状裁剪
        }

        itemBean.VideoName?.let {
            holder.tvname.text = it
        }

        holder.itemView.findViewById<ImageView>(R.id.video).setOnClickListener { view ->
            var position: Int = holder.adapterPosition
            itemClickListener?.onItemClick(position+1)
        }
    }


    override fun getItemCount(): Int {
        return VideoList.size
    }

    private fun outlinebundary(image: ImageView){
        // 设置自定义的裁剪轮廓
        image.outlineProvider = object : ViewOutlineProvider() {
            override fun getOutline(view: View, outline: Outline) {
                // 定义裁剪轮廓
                val cornerRadius = 100f // 圆角半径，单位为像素
                outline.setRoundRect(0, 0, image.width, image.height, cornerRadius)
            }
        }
        image.clipToOutline = true;
    }
}