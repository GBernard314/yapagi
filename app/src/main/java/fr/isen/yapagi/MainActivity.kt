package fr.isen.yapagi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import fr.isen.yapagi.data.Comment
import fr.isen.yapagi.data.Post
import fr.isen.yapagi.data.User
import fr.isen.yapagi.databinding.ActivityMainBinding
import fr.isen.yapagi.network.Database
import java.time.Instant.now
import java.time.LocalDate
import java.util.*


class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, FeedActivity::class.java)
        intent.putExtra("username", "Franck")
        startActivity(intent);



        displayMsg("Feed");
        var comments: ArrayList<Comment> = ArrayList()
        comments.add(Comment(Date(), "Super !", "Yanis"))
        comments.add(Comment(Date(), "Nul...", "Gabi"))

        Database.createPost(Post("yanis", Date(), "Super photo !", 73, comments.toList(), "img_url"))
    }

    public fun displayMsg(str: String) {
        Toast.makeText(this, "Clicked : " + str, Toast.LENGTH_SHORT).show();
    }

}