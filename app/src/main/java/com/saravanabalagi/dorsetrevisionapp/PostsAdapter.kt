package com.saravanabalagi.dorsetrevisionapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.saravanabalagi.dorsetrevisionapp.models.Post

class PostsAdapter(private val posts: Array<Post>, private val context: Context): RecyclerView.Adapter<PostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.template_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val postContent = holder.itemView.findViewById<TextView>(R.id.post_content)
        if (position > 3) {
            postContent.text = context.getString(R.string.template_post_content, position)
        }
    }

    override fun getItemCount(): Int {
        return posts.size
    }
}