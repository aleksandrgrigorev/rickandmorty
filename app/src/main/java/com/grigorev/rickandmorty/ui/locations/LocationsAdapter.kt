package com.grigorev.rickandmorty.ui.locations

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.grigorev.rickandmorty.R
import com.grigorev.rickandmorty.databinding.LocationItemBinding
import com.grigorev.rickandmorty.dto.Location

class LocationsAdapter(
    private val locations: List<Location>,
) :  RecyclerView.Adapter<LocationsAdapter.ViewHolder>() {

    inner class ViewHolder(binding: LocationItemBinding) : RecyclerView.ViewHolder(binding.root) {

        val name = binding.name
        val type = binding.type
        val dimension = binding.dimension

        val locationItem = binding.locationItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationsAdapter.ViewHolder {
        val binding = LocationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LocationsAdapter.ViewHolder, position: Int) {
        val location = locations[position]

        holder.apply {
            name.text = itemView.context.getString(R.string.name, location.name)
            dimension.text = itemView.context.getString(R.string.dimension, location.dimension)
            type.text = itemView.context.getString(R.string.type, location.type)

            val bundle = bundleOf("id" to location.id)
            locationItem.setOnClickListener {
                Navigation.findNavController(it).navigate(
                    R.id.action_navigation_locations_to_locationDetailsFragment, bundle
                )
            }
        }
    }

    override fun getItemCount(): Int = locations.size

}