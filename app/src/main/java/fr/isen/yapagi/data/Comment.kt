package fr.isen.yapagi.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

data class Comment(
    @SerializedName("date")val date: Date,
    @SerializedName("content")val content: String,
    @SerializedName("user")val user: String
): Serializable {}