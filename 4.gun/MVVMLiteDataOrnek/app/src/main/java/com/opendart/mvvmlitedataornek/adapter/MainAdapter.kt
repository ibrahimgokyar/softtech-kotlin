package com.opendart.mvvmlitedataornek.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.opendart.mvvmlitedataornek.R

import com.bumptech.glide.Glide
import com.opendart.mvvmlitedataornek.DetailActivity
import com.opendart.mvvmlitedataornek.databinding.LayoutRvItemBinding
import com.opendart.mvvmlitedataornek.model.Hero


class MainAdapter : RecyclerView.Adapter<MainViewHolder>() {
    var movies  = mutableListOf<Hero>()

    fun setMovieList(movies: List<Hero>)
    {
        this.movies = movies.toMutableList()
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater  = LayoutInflater.from(parent.context)
        val binding = LayoutRvItemBinding.inflate(inflater,parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val movie = movies[position]
        holder.binding.movieTitle.text = movie.name
        holder.binding.imagePath.text  = movie.team
        holder.binding.imageUrl.text = movie.imageurl
        Glide.with(holder.itemView.context).load(movie.imageurl).placeholder(R.drawable.placeholder)
            .into(holder.binding.moviePoster)
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}

class MainViewHolder(val binding : LayoutRvItemBinding) : RecyclerView.ViewHolder(binding.root)
{
    //detaya buradan gidecez

    init {
        itemView.setOnClickListener{
            val intent = Intent(itemView.context, DetailActivity::class.java)
            intent.putExtra("title",binding.movieTitle.text)
            intent.putExtra("imagePath",binding.imagePath.text)
            intent.putExtra("imageurl",binding.imageUrl.text)
            itemView.context.startActivity(intent)
        }
    }
}