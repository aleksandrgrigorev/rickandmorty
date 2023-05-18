package com.grigorev.rickandmorty.ui.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.grigorev.rickandmorty.INITIAL_PAGE
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

        binding.swipeRefreshLayout.setOnRefreshListener {
            charactersViewModel.loadCharacters(INITIAL_PAGE)
            binding.swipeRefreshLayout.isRefreshing = false
        }

        lifecycleScope.launch {
            charactersViewModel.flow.collect {
                val adapter = context?.let { context -> CharactersAdapter(it, context) }
                binding.charactersList.adapter = adapter
            }
        }

        return binding.root
    }
}