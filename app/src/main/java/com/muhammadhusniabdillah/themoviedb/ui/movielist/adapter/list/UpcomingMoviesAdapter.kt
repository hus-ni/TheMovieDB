package com.muhammadhusniabdillah.themoviedb.ui.movielist.adapter.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.muhammadhusniabdillah.themoviedb.BuildConfig
import com.muhammadhusniabdillah.themoviedb.data.network.dto.MoviesDetails
import com.muhammadhusniabdillah.themoviedb.databinding.MoviesItemListBinding

class UpcomingMoviesAdapter(
    private val onMoviePosterClick: (MoviesDetails) -> Unit
) : PagingDataAdapter<MoviesDetails, UpcomingMoviesAdapter.MoviesViewHolder>(DIFF_UTIL) {

    inner class MoviesViewHolder(private val binding: MoviesItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movieList: MoviesDetails) {
            with(binding) {
//                val circularLoading = CircularProgressDrawable(root.context).apply {
//                    strokeWidth = 12f
//                    centerRadius = 48f
//                }
//                circularLoading.start()

                Glide.with(root)
                    .load("${BuildConfig.BASE_POSTER_URL}${movieList.posterPath}")
                    .transform(CenterCrop())
                    .into(itemMoviePoster)

                itemView.setOnClickListener { onMoviePosterClick.invoke(movieList) }
            }
        }
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val moviesList = getItem(position)
        moviesList?.let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val binding =
            MoviesItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(binding)
    }

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<MoviesDetails>() {
            override fun areItemsTheSame(
                oldItem: MoviesDetails,
                newItem: MoviesDetails
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: MoviesDetails,
                newItem: MoviesDetails
            ): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }
}