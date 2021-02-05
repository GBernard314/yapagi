package fr.isen.yapagi.data

import java.io.Serializable

data class User(
    val firstName: String? = null,
    val lastName: String? = null,
    val username: String? = null,
    val email: String? = null
): Serializable {}