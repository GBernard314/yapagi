package fr.isen.yapagi.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

data class Post(
        @SerializedName("username") val username: String,
        @SerializedName("date") val date: Date,
        @SerializedName("description") val description: String,
        @SerializedName("nb_likes") val nb_likes: Int,
        @SerializedName("comments") val comments: List<Comment>,
        @SerializedName("url") val url: String
): Serializable {

        fun getCommentsCount(): Int {
                var count = 0
                for (com in comments){
                        count += 1
                }
                return count
        }
}