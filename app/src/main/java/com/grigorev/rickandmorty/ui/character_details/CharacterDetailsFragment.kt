package com.grigorev.rickandmorty.ui.character_details

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.grigorev.rickandmorty.R
import com.grigorev.rickandmorty.databinding.FragmentCharacterDetailsBinding
import kotlinx.coroutines.launch

class CharacterDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentCharacterDetailsBinding.inflate(inflater, container, false)

        val bundleId = requireArguments().getInt("id")

        val characterViewModel = CharacterDetailsViewModel(Application(), bundleId)

        lifecycleScope.launch {
            characterViewModel.loadCharacter(bundleId)
            characterViewModel.flow.collect {
                binding.apply {
                    name.text = getString(R.string.name, it.name)
                    species.text = getString(R.string.species, it.species)
                    gender.text = getString(R.string.gender, it.gender)
                    status.text = getString(R.string.status, it.status)
                    origin.text = getString(R.string.origin, it.origin.name)
                    location.text = getString(R.string.location, it.location.name)

                    Glide.with(this@CharacterDetailsFragment)
                        .load(it.image)
                        .into(image)

                    val episodes = it.episode

                }
            }
        }



        return binding.root
    }

}