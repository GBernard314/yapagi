package fr.isen.yapagi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.yapagi.databinding.ActivityFeedBinding

lateinit var binding_feed: ActivityFeedBinding
class FeedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding_feed = ActivityFeedBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_feed)
        title = "Feed"
        setContentView(binding_feed.root)
        title = intent.getStringExtra("username")

        binding_feed.postList.layoutManager = LinearLayoutManager(this)

    }
}