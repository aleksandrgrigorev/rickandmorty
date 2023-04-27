package com.grigorev.rickandmorty.ui.locations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.grigorev.rickandmorty.databinding.FragmentLocationsBinding
import kotlinx.coroutines.launch

class LocationsFragment : Fragment() {

    private val locationsViewModel: LocationsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentLocationsBinding.inflate(inflater, container, false)

        lifecycleScope.launch {
            locationsViewModel.flow.collect {
                val adapter = LocationsAdapter(it)
                binding.locationsList.adapter = adapter
            }
        }
        return binding.root
    }
}