package com.saravanabalagi.dorsetrevisionapp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.saravanabalagi.dorsetrevisionapp.models.Post
import com.saravanabalagi.dorsetrevisionapp.models.User
import kotlinx.android.synthetic.main.activity_posts.*
import okhttp3.*
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

class PostsActivity: AppCompatActivity(R.layout.activity_posts) {

    private lateinit var client: OkHttpClient
    private lateinit var users: Array<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        client = OkHttpClient()
        makeAddPostRequest()
        makeUsersRequest()
    }

    private fun makeAddPostRequest() {
        val url = "https://jsonplaceholder.typicode.com/users"
        val newPost = Post().apply {
            userId = 3
            body = "Some body"
            title = "Some title"
        }
        val request = Request.Builder().url(url).post(Gson().toJson(newPost).toRequestBody()).build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e(POSTS_ACTIVITY_LOG_KEY, "AddPost Failed: ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                Log.i(POSTS_ACTIVITY_LOG_KEY, "AddPost successful, response received, Status code: ${response.code}")
            }
        })
    }

    private fun makeUsersRequest() {
        val url = "https://jsonplaceholder.typicode.com/users"
        val request = Request.Builder().url(url).build()
        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e(POSTS_ACTIVITY_LOG_KEY, "UsersRequest Failed: ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful && response.body != null) {
                    val responseBody = response.body!!.string()
                    users = Gson().fromJson(responseBody, Array<User>::class.java)
                    users.forEach { Log.i(POSTS_ACTIVITY_LOG_KEY, it.toString()) }
                    makePostsRequest()
                } else {
                    Log.e(POSTS_ACTIVITY_LOG_KEY, "Users response received, Status code: ${response.code}")
                }
            }
        })
    }

    private fun makePostsRequest() {
        val url = "https://jsonplaceholder.typicode.com/posts"
        val request = Request.Builder().url(url).build()
        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e(POSTS_ACTIVITY_LOG_KEY, "PostsRequest Failed: ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful && response.body != null) {
                    val responseBody = response.body!!.string()
                    val posts = Gson().fromJson(responseBody, Array<Post>::class.java)
                    posts.forEach {
                        it.user = users.filter { e -> e.id == it.userId }[0]
                        Log.i(POSTS_ACTIVITY_LOG_KEY, it.toString())
                    }

                    // ui updates
                    Handler(Looper.getMainLooper()).post {
                        Toast.makeText(this@PostsActivity, "Posts parsing complete, Total posts: ${posts.size}", Toast.LENGTH_LONG).show()

                        loading_text.visibility = View.GONE
                        posts_recycler_view.visibility = View.VISIBLE
                        posts_recycler_view.layoutManager = LinearLayoutManager(this@PostsActivity)
                        posts_recycler_view.adapter = PostsAdapter(posts, this@PostsActivity)
                    }

                } else {
                    Log.e(POSTS_ACTIVITY_LOG_KEY, "Posts response received, Status code: ${response.code}")
                }
            }
        })
    }
}