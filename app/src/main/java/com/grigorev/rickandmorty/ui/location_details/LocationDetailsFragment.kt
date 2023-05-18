package com.grigorev.rickandmorty.ui.location_details

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.grigorev.rickandmorty.R
import com.grigorev.rickandmorty.databinding.FragmentLocationDetailsBinding
import kotlinx.coroutines.launch

class LocationDetailsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentLocationDetailsBinding.inflate(inflater, container, false)
        val bundleId = requireArguments().getInt("id")
        val locationViewModel = LocationDetailsViewModel(Application(), bundleId)

        lifecycleScope.launch {
            locationViewModel.loadLocationById(bundleId)
            locationViewModel.flow.collect {
                binding.apply {
                    name.text = it.name
                    dimension.text = getString(R.string.dimension, it.dimension)
                    type.text = getString(R.string.type, it.type)
                }
            }
        }

        return binding.root
    }

}