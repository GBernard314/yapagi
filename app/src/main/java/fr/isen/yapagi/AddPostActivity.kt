package fr.isen.yapagi

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import fr.isen.yapagi.data.Comment
import fr.isen.yapagi.data.Post
import fr.isen.yapagi.databinding.ActivityAddPostBinding
import java.io.File
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

private lateinit var binding: ActivityAddPostBinding

class AddPostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPostBinding.inflate(layoutInflater);
        setContentView(binding.root)

        binding.submitButton.setOnClickListener {
            createPost()
            intent = Intent(this, FeedActivity::class.java)
        }

        binding.fileExplorer.setOnClickListener{
            val intent = Intent()
                .setType("*/*")
                .setAction(Intent.ACTION_GET_CONTENT)

            startActivityForResult(Intent.createChooser(intent, "Select a file"), 777)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 777) {
            val filePath = data?.data?.path
            val file = File(filePath)
            Picasso.get()
                .load(file)
                .placeholder(R.drawable.gear)
                .into(binding.image)
        }
    }


    fun createPost(): String {
        val sharedPreferences = getSharedPreferences(APP_PREFS, MODE_PRIVATE)
        var username = sharedPreferences.getString("username", "Unknown User")

        val date = Calendar.getInstance().getTime()
        val formatedDate  = SimpleDateFormat("yyyy-MM-dd").format(date)

        var description = binding.descriptionInput.text.toString()
        var nb_likes = 0;
        var comments = listOf<Comment>()
        var url = binding.imageURL.text.toString()

        var post = username?.let { Post(it, formatedDate, description, nb_likes, comments, url) }
        var postToReturn = GsonBuilder().setPrettyPrinting().create().toJson(post, Post::class.java)
        return postToReturn
    }


    companion object {
        const val APP_PREFS = "app_prefs"
    }
}