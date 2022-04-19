package com.saravanabalagi.dorsetrevisionapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // kotlin synthetics
        open_new_page_button.setOnClickListener {
//            Toast.makeText(this, "Button Clicked", Toast.LENGTH_LONG).show()
            val intent = Intent(this, PostsActivity::class.java)
            startActivity(intent)
        }

        // if you don't want to use kotlin synthetics use findViewById
//        val openNewPageButton = findViewById<Button>(R.id.open_new_page_button)
//        openNewPageButton
    }
}