package fr.isen.yapagi.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Comment(
    @SerializedName("date")val date: String,
    @SerializedName("content")val content: String,
    @SerializedName("user")val user: String
): Serializable {}