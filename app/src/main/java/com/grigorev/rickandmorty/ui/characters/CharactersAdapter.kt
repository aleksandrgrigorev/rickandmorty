package com.grigorev.rickandmorty.ui.characters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.grigorev.rickandmorty.R
import com.grigorev.rickandmorty.databinding.CharacterItemBinding
import com.grigorev.rickandmorty.dto.Character

class CharactersAdapter(
    private val characters: List<Character>,
    private val context: Context
) :  RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {

    inner class ViewHolder(binding: CharacterItemBinding) : RecyclerView.ViewHolder(binding.root) {

        val image = binding.image
        val name = binding.name
        val species = binding.species
        val gender = binding.gender
        val status = binding.status

        val characterItem = binding.characterItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersAdapter.ViewHolder {
        val binding = CharacterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharactersAdapter.ViewHolder, position: Int) {
        val character = characters[position]

        holder.apply {
            name.text = itemView.context.getString(R.string.name, character.name)
            species.text = itemView.context.getString(R.string.species, character.species)
            gender.text = itemView.context.getString(R.string.gender, character.gender)
            status.text = itemView.context.getString(R.string.status, character.status)

            Glide.with(context)
                .load(character.image)
                .into(image)

            val bundle = bundleOf("id" to character.id)
            characterItem.setOnClickListener {
                Navigation.findNavController(it).navigate(
                    R.id.action_navigation_characters_to_characterDetailsFragment, bundle
                )

            }
        }
    }

    override fun getItemCount(): Int = characters.size

}