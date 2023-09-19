package com.example.myapplication.Model

class User {
    private var username: String? = null
    private var password: String? = null
    private var email: String? = null

    fun User(
        username: String?,
        password: String?,
        email: String?,
    ) {
        this.username = username
        this.email = email
        this.password = password
    }


    fun getUsername(): String? {
        return username
    }

    fun setUsername(username: String?) {
        this.username = username
    }

    fun getEmail(): String? {
        return email
    }

    fun setEmail(email: String?) {
        this.email = email
    }

    fun getPassword(): String? {
        return password
    }

    fun setPassword(password: String?) {
        this.password = password
    }
}