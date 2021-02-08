package fr.isen.yapagi

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import fr.isen.yapagi.data.Comment
import fr.isen.yapagi.data.Post
import fr.isen.yapagi.data.User
import fr.isen.yapagi.databinding.ActivityMainBinding
import fr.isen.yapagi.network.Database
import fr.isen.yapagi.network.PostsDataListener
import java.time.Instant.now
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*


class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, FeedActivity::class.java)
        intent.putExtra("username", "Franck")
        startActivity(intent);



        displayMsg("Feed");

        var comments: ArrayList<Comment> = ArrayList()

        comments.add(Comment(LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)), "Oh waw trop mims quoi !", "Pierre"))
        comments.add(Comment(LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)), "I like cats !", "Guillaume"))
        Database.createPost(Post("Chien", LocalDateTime
            .now()
            .format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)),
            "wouf wouf",
            999999,
            comments.toList(), "https://instagram.fcdg2-1.fna.fbcdn.net/v/t51.2885-15/e35/51993659_572254839943780_6273454790177071551_n.jpg?_nc_ht=instagram.fcdg2-1.fna.fbcdn.net&_nc_cat=104&_nc_ohc=Jp3iQtqHAAwAX9pXCbm&tp=1&oh=7dfdd96c69a9985b86444b88eca3ea81&oe=604657E5"))

        Database.getPosts(object: PostsDataListener{
            override fun onSuccess(value: Map<String, Post>?){
                if (value != null) {
                    for(post in value){
                        Log.d("GET_POSTS", post.value.toString())
                    }
                }
            }
        })
    }

    public fun displayMsg(str: String) {
        Toast.makeText(this, "Clicked : " + str, Toast.LENGTH_SHORT).show();
    }

}