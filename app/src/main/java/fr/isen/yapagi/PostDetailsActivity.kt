package fr.isen.yapagi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import fr.isen.yapagi.data.Post
import fr.isen.yapagi.databinding.ActivityPostDetailsBinding

lateinit var binding_details: ActivityPostDetailsBinding
class PostDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_details)
        binding_details = ActivityPostDetailsBinding.inflate(layoutInflater);
        binding_details.comments.layoutManager = LinearLayoutManager(this)

        setContentView(binding_details.root)
        val the_post = intent.getSerializableExtra("post") as Post

        binding_details.comments.adapter = CommentsRecyclerViewAdapter(the_post.comments, this)


        Picasso.get()
            .load(the_post.url)
            .placeholder(R.drawable.gear)// Image to load when something goes wrong
            .into(binding_details.imagePost);


        var liked = false

        binding_details.user.text = the_post.username
        binding_details.description.text = the_post.description
        val nb_like = binding_details.nbLikes
        nb_like.text = the_post.nb_likes.toString()
        val nb_Com = binding_details.nbComments
        nb_Com.text = the_post.comments.size.toString()
        val like = binding_details.like
        val like2 = binding_details.imageView2
        val container: CardView = binding_details.root;



        like2.setOnClickListener {
            like2.visibility = View.GONE
            if (!liked) {
                liked = true
                nb_like.text = (the_post.nb_likes.toString().toInt() + 1).toString()

                like.visibility = View.VISIBLE
                like.playAnimation()
            }

        }

        binding_details.floatingActionButton2.setOnClickListener {
            startActivity(Intent(this, AddPostActivity::class.java))
        }

        like.setOnClickListener {
            if (liked) {
                liked = false
                nb_like.text = (the_post.nb_likes.toString())
                like.visibility = View.GONE
                like2.visibility = View.VISIBLE
            }

        }


    }
}
