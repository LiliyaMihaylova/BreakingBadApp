package com.volasoftware.breakingbadapp.ui.adapters.characters

import com.squareup.picasso.Picasso
import com.volasoftware.breakingbadapp.R
import com.volasoftware.breakingbadapp.databinding.CharacterItemBinding
import com.volasoftware.breakingbadapp.networking.models.Character
import com.volasoftware.breakingbadapp.ui.adapters.BaseViewHolder
import com.volasoftware.breakingbadapp.utils.PixelConverter

class CharactersViewHolder(
    itemView: CharacterItemBinding,
    private val listener: OnItemSelectListener
) :
    BaseViewHolder<CharacterItemBinding>(itemView) {

    fun bind(character: Character) {
        binding.txtCharacterName.text = character.name
        loadImage(character.img)

        binding.itemContainer.setOnClickListener {
            listener.onItemSelected(character)
        }
    }

    private fun loadImage(url: String) {
        Picasso.get()
            .load(url)
            .resize(
                PixelConverter.dpToPx(WIDTH),
                PixelConverter.dpToPx(HEIGHT)
            )
            .placeholder(R.drawable.ic_person_placeholder)
            .error(R.drawable.ic_person_placeholder)
            .into(binding.imgCharacter)
    }

    companion object {
        private const val WIDTH = 72
        private const val HEIGHT = 96
    }
}