package fr.isen.yapagi

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import fr.isen.yapagi.data.Post
import com.squareup.picasso.Picasso
import fr.isen.yapagi.data.Comment
import fr.isen.yapagi.databinding.ActivityPostDetailsBinding
import fr.isen.yapagi.databinding.CommentCardBinding


class CommentsRecyclerViewAdapter(
    private val dataSet: List<Comment>,
    private val ct: Context
):
    RecyclerView.Adapter<CommentsRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(binding: CommentCardBinding) : RecyclerView.ViewHolder(binding.root) {
        val user = binding.user
        val com = binding.com
        val date = binding.dateCom
        val container: CardView = binding.root;


    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val itemBinding = CommentCardBinding.inflate(LayoutInflater.from(parent.context), parent, false);
        return ViewHolder(itemBinding);
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.user.text = dataSet[position].user
        holder.com.text = dataSet[position].content
        holder.date.text = dataSet[position].date.toString()

    }

    override fun getItemCount(): Int {
        return dataSet.size;
    }

}