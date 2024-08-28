package com.jcasben.heroesapp.details

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.jcasben.heroesapp.characters.Character
import com.jcasben.heroesapp.databinding.ItemCharacterBinding
import com.squareup.picasso.Picasso

class CharactersViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private var binding = ItemCharacterBinding.bind(view)

    fun bind(character: Character) {
        Picasso.get().load(character.character.image.jpgImage.url).into(binding.ivCharacter)
        Picasso.get().load(character.actors[0].person.personImage.jpgImage.url).into(binding.ivPerson)
        binding.tvCharacterName.text = character.character.name
        binding.tvCharacterRole.text = character.role
        binding.tvPersonName.text = character.actors[0].person.name
        binding.tvPersonLanguage.text = character.actors[0].language
    }
}