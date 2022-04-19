package com.saravanabalagi.dorsetrevisionapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.saravanabalagi.dorsetrevisionapp.models.Post
import kotlinx.android.synthetic.main.activity_posts.*
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class PostsActivity: AppCompatActivity(R.layout.activity_posts) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        posts_recycler_view.layoutManager = LinearLayoutManager(this)
        posts_recycler_view.adapter = PostsAdapter(this)

//        makeRequest()

        val post = Post().apply {
            id = 100
            title = "My title"
            body = "Some long body"
            userId = 0
        }
        Log.i(POSTS_ACTIVITY_LOG_KEY, post.toString())
    }

    private fun makeRequest() {
        val url = "https://jsonplaceholder.typicode.com/posts"
        val client = okhttp3.OkHttpClient()
        val request = Request.Builder().url(url).build()
        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.i(POSTS_ACTIVITY_LOG_KEY, "Request Failed: ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful && response.body != null) {
                    val responseBody = response.body!!.string()
                    Log.i(POSTS_ACTIVITY_LOG_KEY, "response received: $responseBody")
                } else {
                    Log.i(POSTS_ACTIVITY_LOG_KEY, "response received, Status code: ${response.code}")
                }
            }
        })

    }
}