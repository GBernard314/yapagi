package fr.isen.yapagi

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.yapagi.data.Comment
import fr.isen.yapagi.data.Post
import fr.isen.yapagi.databinding.ActivityFeedBinding
import fr.isen.yapagi.network.Database
import fr.isen.yapagi.network.PostsDataListener
import java.util.*

lateinit var binding_feed: ActivityFeedBinding
class FeedActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding_feed = ActivityFeedBinding.inflate(layoutInflater)
        //title = "Feed"
        setContentView(binding_feed.root)
        title = intent.getStringExtra("username")

        binding_feed.postList.layoutManager = LinearLayoutManager(this)

        makeRequest()

        binding_feed.floatingActionButton2.setOnClickListener {
            startActivity(Intent(this, AddPostActivity::class.java))
        }

    }
    private fun makeRequest() {
        Database.getPosts(object: PostsDataListener {
            override fun onSuccess(value: Map<String, Post>?){
                var list: List<Post> = emptyList()
                value?.forEach { list += it.value }
                binding_feed.postList.adapter = FeedRecyclerViewAdapter(list, applicationContext)
            }
        })
    }


}
