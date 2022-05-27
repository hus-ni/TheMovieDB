package com.muhammadhusniabdillah.themoviedb.ui.movielist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.muhammadhusniabdillah.themoviedb.databinding.MoviesLoadingItemListBinding

class LoadingAdapter() : LoadStateAdapter<LoadingAdapter.LoadingStateViewHolder>() {

    class LoadingStateViewHolder(
        private val binding: MoviesLoadingItemListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(loadingState: LoadState) {
            binding.progressBar.isVisible = loadingState is LoadState.Loading
        }
    }

    override fun onBindViewHolder(
        holder: LoadingStateViewHolder,
        loadState: LoadState
    ) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoadingStateViewHolder {
        val binding =
            MoviesLoadingItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoadingStateViewHolder(binding)
    }


}