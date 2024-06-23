package com.jcasben.heroesapp.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jcasben.heroesapp.R
import com.jcasben.heroesapp.characters.Character

class CharactersAdapter(private var characters: List<Character> = emptyList()) :
    RecyclerView.Adapter<CharactersViewHolder>() {

    fun updateList(characters: List<Character>) {
        this.characters = characters
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        return CharactersViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        )
    }

    override fun getItemCount() = characters.size

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        holder.bind(characters[position])
    }
}