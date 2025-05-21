package com.opendart.retrofitsinemaornek.viewholder

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.content.Intent
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.opendart.retrofitsinemaornek.R
import com.opendart.retrofitsinemaornek.activities.MovieDetailActivity
import com.opendart.retrofitsinemaornek.dto.Hero

//listedeki her bir eleman için burası çalışacak

class HeroesViewHolder(viewGroup: ViewGroup) : RecyclerView.ViewHolder(
LayoutInflater.from(viewGroup.context).inflate(R.layout.heroes_list_item,viewGroup,false))
{

    private val txtName by lazy { itemView.findViewById<TextView>(R.id.txtName) }
    private val txtRealName by lazy { itemView.findViewById<TextView>(R.id.txtRealName) }
    private val txtFirstAppearance by lazy { itemView.findViewById<TextView>(R.id.txtFirstAppearance) }
    private val txtBio by lazy { itemView.findViewById<TextView>(R.id.txtBio) }
    private val imgViewImage by lazy { itemView.findViewById<ImageView>(R.id.imgViewImageUrl) }

    fun bindTo(heroDto: Hero) {
        txtName.text = heroDto.name
        txtRealName.text = heroDto.realname
        txtFirstAppearance.text = heroDto.firstappearance
        txtBio.text = heroDto.bio
        Glide.with(itemView.context).load(heroDto.imageurl)
            .thumbnail(
                Glide.with(itemView.context)
                    .load(androidx.appcompat.R.drawable.abc_ic_go_search_api_material)
            )
            .transition(withCrossFade()).into(imgViewImage)

        itemView.setOnClickListener {
            val intent = Intent(itemView.context, MovieDetailActivity::class.java)
            intent.putExtra("bio",heroDto.bio)
            intent.putExtra("imageurl",heroDto.imageurl)
            itemView.context.startActivity(intent)
        }
    }
}