package com.jcasben.heroesapp.animes

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.jcasben.heroesapp.databinding.ItemAnimeBinding
import com.squareup.picasso.Picasso

class AnimeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private var binding = ItemAnimeBinding.bind(view)

    fun bind(animeItem: Anime, onItemSelected: (String) -> Unit) {
        binding.tvAnimeName.text = animeItem.title
        Picasso.get().load(animeItem.image.jpgImage.url).into(binding.ivAnime)
        binding.root.setOnClickListener { onItemSelected(animeItem.id) }
    }
}
