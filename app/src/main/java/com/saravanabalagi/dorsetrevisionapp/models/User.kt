package com.saravanabalagi.dorsetrevisionapp.models

class User {
    var id: Int = -1
    var name: String = ""
    var username: String = ""
    var email: String = ""

    override fun toString(): String {
        return "User $id: $username, $name"
    }
}