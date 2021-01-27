package com.volasoftware.breakingbadapp.ui.adapters.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.volasoftware.breakingbadapp.databinding.CharacterItemBinding
import com.volasoftware.breakingbadapp.networking.models.Character

class CharactersAdapter(private val listener: OnItemSelectListener) :
    RecyclerView.Adapter<CharactersViewHolder>() {

    var items = arrayListOf<Character>()

    fun setItems(characterItem: List<Character>) {
        if (characterItem != null) {
            items.clear()
            items.addAll(characterItem)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        return CharactersViewHolder(
            CharacterItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), listener
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        holder.bind(items[position])
    }
}


interface OnItemSelectListener {
    fun onItemSelected(item: Character)
}