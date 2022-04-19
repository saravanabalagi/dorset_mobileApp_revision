package com.saravanabalagi.dorsetrevisionapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.saravanabalagi.dorsetrevisionapp.models.Post
import com.saravanabalagi.dorsetrevisionapp.models.User

class PostsAdapter(private val posts: Array<Post>, private val users: Array<User>, private val context: Context): RecyclerView.Adapter<PostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.template_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val currentPost: Post = posts[position]
        val postTitle = holder.itemView.findViewById<TextView>(R.id.post_title)
        val postBody = holder.itemView.findViewById<TextView>(R.id.post_body)
        val postUser = holder.itemView.findViewById<TextView>(R.id.post_user)
        postTitle.text = currentPost.title
        postBody.text = currentPost.body
        postUser.text = currentPost.userId.toString()
    }

    override fun getItemCount(): Int {
        return posts.size
    }
}