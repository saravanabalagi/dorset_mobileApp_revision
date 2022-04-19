package com.saravanabalagi.dorsetrevisionapp.models

class Post {
    var id: Int = -1
    var title: String = ""
    var body: String = ""
    var userId: Int = -1

    override fun toString(): String {
        return "Post $id: $title"
    }
}