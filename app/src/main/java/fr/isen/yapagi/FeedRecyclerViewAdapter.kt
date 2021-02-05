package fr.isen.yapagi

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import fr.isen.yapagi.data.Post
import fr.isen.yapagi.databinding.PostBinding

class FeedRecyclerViewAdapter(
    private val dataSet: List<Post>,
    private val ct: Context
):
    RecyclerView.Adapter<FeedRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(binding: PostBinding) : RecyclerView.ViewHolder(binding.root) {
        val title = binding.postTitle
        /*
        val price = binding.DishPrice
        val image = binding.DishImage
        */
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
        holder.title.text = dataSet[position].title
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