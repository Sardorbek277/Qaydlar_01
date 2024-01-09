package com.example.qaydlar_01.db

import com.example.qaydlar_01.models.User

interface MyInterface {
    fun addUser(user: User)
    fun getUsers():ArrayList<User>

}