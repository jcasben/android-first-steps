package com.jcasben.heroesapp.animes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jcasben.heroesapp.R

class AnimeAdapter(
    var animeList: List<Anime> = emptyList(),
    private val onItemSelected: (Int) -> Unit
) :
    RecyclerView.Adapter<AnimeViewHolder>() {

    fun updateList(animeList: List<Anime>) {
        this.animeList = animeList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        return AnimeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_anime, parent, false)
        )
    }

    override fun getItemCount() = animeList.size

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        holder.bind(animeList[position], onItemSelected)
    }

}
