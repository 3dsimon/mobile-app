package com.example.bigproject.newsrecycler

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bigproject.R


class MyRecyclerAdapter : RecyclerView.Adapter<MyRecyclerViewHolder> {

    /** 保存数据的列表 */
    private val newsList: ArrayList<NewsItem>
    /**监听点击事件触发*/
    private var itemClickListener:ItemClickListener?=null
    interface ItemClickListener {
        fun onItemClick(position: Int)
        fun onDeleteClick(position: Int)
    }
    constructor(demoList: ArrayList<NewsItem>,itemClickListener: ItemClickListener) {
        this.newsList= demoList
        this.itemClickListener=itemClickListener
    }
    /** 创建视口载体 */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRecyclerViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return MyRecyclerViewHolder(view)
    }

    /** 绑定数据 */
    override fun onBindViewHolder(holder: MyRecyclerViewHolder, position: Int) {
        val itemBean = newsList[position]

        itemBean.coverUrl?.let {
            holder.ivCover.setImageBitmap(it)
        }

        itemBean.title?.let {
            holder.tvTitle.text = it
        }

        itemBean.author?.let {
            holder.tvAuthor.text = it
        }

        itemBean.type?.let {
            holder.tvType.text = it
            if(it=="热点")holder.tvType.setTextColor(Color.rgb(255,97,0))
            if(it=="时事")holder.tvType.setTextColor(Color.rgb(0,201,87))
            if(it=="要闻")holder.tvType.setTextColor(Color.rgb(227,23,13))
            if(it=="最新")holder.tvType.setTextColor(Color.rgb(135,206,235))
        }

        holder.itemView.findViewById<TextView>(R.id.tv_title).setOnClickListener { view ->
                var position: Int = holder.adapterPosition
                itemClickListener?.onItemClick(position)
        }
        holder.itemView.findViewById<TextView>(R.id.tv_author).setOnClickListener { view ->
            var position: Int = holder.adapterPosition
            itemClickListener?.onItemClick(position)
        }
        holder.itemView.findViewById<ImageView>(R.id.cover).setOnClickListener { view ->
            var position: Int = holder.adapterPosition
            itemClickListener?.onItemClick(position)
        }
        holder.itemView.findViewById<TextView>(R.id.tv_type).setOnClickListener { view ->
            var position: Int = holder.adapterPosition
            itemClickListener?.onItemClick(position)
        }
        holder.itemView.findViewById<ImageView>(R.id.delete).setOnClickListener { view ->
            var position: Int = holder.adapterPosition
            itemClickListener?.onDeleteClick(position)
        }
        val horizontalScrollView=holder.itemView.findViewById<HorizontalScrollView>(R.id.news_scroll)
        horizontalScrollView.post(Runnable { horizontalScrollView.fullScroll(ScrollView.FOCUS_RIGHT) })//scroll设置成一开始显示最右侧内容
        // 绑定数据到 ViewHolder
    }

    override fun getItemCount(): Int {
        return newsList.size
    }
}