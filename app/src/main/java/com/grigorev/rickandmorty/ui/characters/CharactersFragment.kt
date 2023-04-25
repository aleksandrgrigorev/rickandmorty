package com.grigorev.rickandmorty.ui.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.grigorev.rickandmorty.databinding.FragmentCharactersBinding
import kotlinx.coroutines.launch

class CharactersFragment : Fragment() {

    private val charactersViewModel: CharactersViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentCharactersBinding.inflate(inflater, container, false)

        lifecycleScope.launch {
            charactersViewModel.data.observe(viewLifecycleOwner) {
                val adapter = context?.let { context -> CharactersAdapter(it.characters, context) }
                binding.charactersList.adapter = adapter
            }
        }
        return binding.root
    }
}