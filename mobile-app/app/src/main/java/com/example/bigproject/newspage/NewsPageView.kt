package com.example.bigproject.newspage

import com.example.bigproject.databinding.NewsPageBinding

class NewsPageView(val binding: NewsPageBinding, val model: NewsPageModel) {
    var title= binding.newsTitle
    var author=binding.newsAuthor
    var description=binding.newsDescription
    var content=binding.newsContent
    var image=binding.newsImage
    fun setView(){
        title.text= model.title
        description.text=model.description
        content.text=model.content
        author.text = model.author
        image.setImageBitmap(model.image)
    }
}