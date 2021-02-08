package fr.isen.yapagi.network

import fr.isen.yapagi.data.Post
import fr.isen.yapagi.data.User

interface PostsDataListener{
    fun onSuccess(value: Map<String, Post>?)
}

interface UserDataListener {
    fun onSuccess(value: User?)
}