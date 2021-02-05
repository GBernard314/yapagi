package fr.isen.yapagi.network

import fr.isen.yapagi.data.User

interface UserDataListener {
    fun onSuccess(value: User?)
}