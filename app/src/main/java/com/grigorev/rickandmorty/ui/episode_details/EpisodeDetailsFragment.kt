package com.grigorev.rickandmorty.ui.episode_details

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.grigorev.rickandmorty.R
import com.grigorev.rickandmorty.databinding.FragmentEpisodeDetailsBinding
import kotlinx.coroutines.launch

class EpisodeDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentEpisodeDetailsBinding.inflate(inflater, container, false)
        val bundleId = requireArguments().getInt("id")
        val episodeViewModel = EpisodeDetailsViewModel(Application(), bundleId)

        lifecycleScope.launch {
            episodeViewModel.loadEpisodeById(bundleId)
            episodeViewModel.flow.collect {
                binding.apply {
                    episodeDetails.name.text = it.name
                    episodeDetails.airDate.text = getString(R.string.air_date, it.air_date)
                    episodeDetails.episodeNumber.text =
                        getString(R.string.episode_number, it.episode)
                }
            }
        }

        return binding.root
    }

}