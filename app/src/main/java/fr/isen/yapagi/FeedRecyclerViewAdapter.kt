package fr.isen.yapagi

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import fr.isen.yapagi.data.Post
import fr.isen.yapagi.databinding.PostBinding
import com.squareup.picasso.Picasso

class FeedRecyclerViewAdapter(
    private val dataSet: List<Post>,
    private val ct: Context
):
    RecyclerView.Adapter<FeedRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(binding: PostBinding) : RecyclerView.ViewHolder(binding.root) {
        val user = binding.user
        val comment = binding.comment
        val img = binding.imagePost
        val desc = binding.description
        val nb_like = binding.nbLikes
        val nb_Com = binding.nbComments
        val com_user = binding.comUser
        val com = binding.com
        val like = binding.like
        val like2 = binding.imageView2
        val container: CardView = binding.root;


    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val itemBinding = PostBinding.inflate(LayoutInflater.from(parent.context), parent, false);
        return ViewHolder(itemBinding);
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.user.text = dataSet[position].username
        holder.com_user.text = dataSet[position].comments.firstOrNull()?.user
        holder.com.text = dataSet[position].comments.firstOrNull()?.content
        holder.nb_like.text = dataSet[position].nb_likes.toString()
        holder.nb_Com.text = dataSet[position].getCommentsCount().toString()
        holder.desc.text = dataSet[position].description
        var liked = false

        holder.like2.setOnClickListener{
            holder.like2.visibility = View.GONE
            if (!liked){
                liked = true
                holder.nb_like.text = (dataSet[position].nb_likes.toString().toInt() + 1).toString()

                holder.like.visibility = View.VISIBLE
                holder.like.playAnimation()
            }

        }

        holder.like.setOnClickListener{
         if (liked) {
                liked = false
                holder.nb_like.text = (dataSet[position].nb_likes.toString())
                holder.like.visibility = View.GONE
                holder.like2.visibility = View.VISIBLE
            }
        }


        if (dataSet[position].url.isNullOrEmpty()){
            Picasso.get()
                .load(R.drawable.microsoftteams_image__1_)
                .placeholder(R.drawable.microsoftteams_image__1_)// Image to load when something goes wrong
                .into(holder.img);
        } else {
            Picasso.get()
                .load(dataSet[position].url)
                .error(R.drawable.microsoftteams_image__1_)
                .placeholder(R.drawable.microsoftteams_image__1_)// Image to load when something goes wrong
                .into(holder.img);
        }


        holder.container.setOnClickListener{
            val intent = Intent(ct, PostDetailsActivity::class.java)
            val the_post = dataSet[position]
            intent.putExtra("post", the_post)
            ct.startActivity(intent);
        }

        /*
        holder.price.text = dataSet[position].getFormatedPrice()

        //println(dataSet[position].getFormatedPrice())
        //Picasso.get().load(dataSet[position].getFirstPicture()).into(holder.image);
        Picasso.get()
            .load(dataSet[position].getFirstPicture())
            .error(R.drawable.burger_king_logo!!)
            .placeholder(R.drawable.burger_king_logo)// Image to load when something goes wrong
            .into(holder.image);



        holder.container.setOnClickListener{
            val intent = Intent(ct, DishDetailActivity::class.java)
            intent.putExtra("dish_product", holder.title.text as String)
            val dish = dataSet[position]
            intent.putExtra("dish", dish)
            ct.startActivity(intent);
        }

         */
    }

    override fun getItemCount(): Int {
        return dataSet.size;
    }

}