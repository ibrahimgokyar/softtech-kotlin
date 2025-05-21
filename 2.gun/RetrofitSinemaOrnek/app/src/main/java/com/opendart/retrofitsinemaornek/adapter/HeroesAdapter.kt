package com.opendart.retrofitsinemaornek.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.opendart.retrofitsinemaornek.dto.Hero
import com.opendart.retrofitsinemaornek.viewholder.HeroesViewHolder

class HeroesAdapter(heroesList : List<Hero>)  : RecyclerView.Adapter<HeroesViewHolder>() {
    var heroesList = heroesList
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesViewHolder {
        return HeroesViewHolder(parent) // görselin ayarlandığı kısım
    }

    override fun getItemCount(): Int {
        return heroesList.size
    }

    //gizli bir for döngüsü
    override fun onBindViewHolder(holder: HeroesViewHolder, position: Int) {
        holder.bindTo(heroesList[position])
    }
}