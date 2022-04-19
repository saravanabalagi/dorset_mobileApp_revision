package com.saravanabalagi.dorsetrevisionapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_posts.*

class PostsActivity: AppCompatActivity(R.layout.activity_posts) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        posts_recycler_view.layoutManager = LinearLayoutManager(this)
        posts_recycler_view.adapter = PostsAdapter(this)

        getString(R.string.template_post_content)
    }
}

class PostsAdapter(private val context: Context): RecyclerView.Adapter<PostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.template_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val postContent = holder.itemView.findViewById<TextView>(R.id.post_content)
        if (position == 3) {
            postContent.text = context.getString(R.string.template_post_content, position.toFloat())
        }
    }

    override fun getItemCount(): Int {
        return 5
    }
}

class PostViewHolder(v: View): RecyclerView.ViewHolder(v) {}