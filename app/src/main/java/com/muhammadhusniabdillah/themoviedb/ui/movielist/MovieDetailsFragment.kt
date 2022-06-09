package com.muhammadhusniabdillah.themoviedb.ui.movielist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.muhammadhusniabdillah.themoviedb.BuildConfig
import com.muhammadhusniabdillah.themoviedb.databinding.FragmentDetailsBinding
import com.muhammadhusniabdillah.themoviedb.ui.base.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : BaseFragment<FragmentDetailsBinding>(FragmentDetailsBinding::inflate) {

    private val viewModel: MoviesViewModel by viewModels()
    private val args: MovieDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMovieDetail(args.movieId)

        viewModel.details.observe(viewLifecycleOwner) {
            with(binding) {
                movieTitle.text = it?.title
                movieOverview.text = it?.overview
                movieRating.rating = it?.rating!!.div(2)
                movieReleaseDate.text = it.releaseDate

                Glide.with(requireContext())
                    .load("${BuildConfig.BASE_POSTER_URL}${it.posterPath}")
                    .transform(CenterCrop())
                    .into(moviePoster)

                Glide.with(requireContext())
                    .load("${BuildConfig.BASE_BACKDROP_URL}${it.backdropPath}")
                    .transform(CenterCrop())
                    .into(movieBackdrop)
            }
        }
    }
}