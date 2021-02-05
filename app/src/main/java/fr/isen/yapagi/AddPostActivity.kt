package fr.isen.yapagi

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import fr.isen.yapagi.data.Comment
import fr.isen.yapagi.data.Post
import fr.isen.yapagi.databinding.ActivityAddPostBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

private lateinit var binding: ActivityAddPostBinding

class AddPostActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPostBinding.inflate(layoutInflater);
        setContentView(binding.root)

        binding.submitButton.setOnClickListener {
            val post = createPost()
            println(post)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createPost(): String {
        var username = "Test"

        var date = LocalDateTime
            .now()
            .format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT))

        var description = binding.descriptionInput.text.toString()
        var nb_likes = 0;
        var comments = listOf<Comment>()
        var url = binding.imageURL.text.toString()

        var post = Post(username, date, description, nb_likes, comments, url)
        return GsonBuilder().setPrettyPrinting().create().toJson(post, Post::class.java)
    }
}