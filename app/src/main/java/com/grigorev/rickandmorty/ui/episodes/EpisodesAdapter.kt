package com.grigorev.rickandmorty.ui.episodes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grigorev.rickandmorty.R
import com.grigorev.rickandmorty.databinding.EpisodeItemBinding
import com.grigorev.rickandmorty.dto.Episode

class EpisodesAdapter(
    private val episodes: List<Episode>,
) :  RecyclerView.Adapter<EpisodesAdapter.ViewHolder>() {

    inner class ViewHolder(binding: EpisodeItemBinding) : RecyclerView.ViewHolder(binding.root) {

        val name = binding.name
        val airDate = binding.airDate
        val episodeNumber = binding.episodeNumber

        val episodeItem = binding.episodeItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodesAdapter.ViewHolder {
        val binding = EpisodeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EpisodesAdapter.ViewHolder, position: Int) {
        val episode = episodes[position]

        holder.apply {
            name.text = episode.name
            airDate.text = itemView.context.getString(R.string.air_date, episode.air_date)
            episodeNumber.text = itemView.context.getString(R.string.episode_number, episode.episode)
        }
    }

    override fun getItemCount(): Int = episodes.size

}