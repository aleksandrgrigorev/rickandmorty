package com.grigorev.rickandmorty.ui.episodes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.grigorev.rickandmorty.INITIAL_PAGE
import com.grigorev.rickandmorty.databinding.FragmentEpisodesBinding
import kotlinx.coroutines.launch

class EpisodesFragment : Fragment() {

    private val episodesViewModel: EpisodesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentEpisodesBinding.inflate(inflater, container, false)

        binding.swipeRefreshLayout.setOnRefreshListener {
            episodesViewModel.loadEpisodes(INITIAL_PAGE)
            binding.swipeRefreshLayout.isRefreshing = false
        }

        lifecycleScope.launch {
            episodesViewModel.flow.collect {
                val adapter = EpisodesAdapter(it)
                binding.episodesList.adapter = adapter
            }
        }
        return binding.root
    }
}