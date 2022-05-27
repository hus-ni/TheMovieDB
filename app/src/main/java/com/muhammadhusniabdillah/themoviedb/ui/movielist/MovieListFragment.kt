package com.muhammadhusniabdillah.themoviedb.ui.movielist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.muhammadhusniabdillah.themoviedb.R
import com.muhammadhusniabdillah.themoviedb.databinding.FragmentHomeBinding
import com.muhammadhusniabdillah.themoviedb.ui.base.fragment.BaseFragment
import com.muhammadhusniabdillah.themoviedb.ui.movielist.adapter.LoadingAdapter
import com.muhammadhusniabdillah.themoviedb.ui.movielist.adapter.list.PopularMoviesListAdapter
import com.muhammadhusniabdillah.themoviedb.ui.movielist.adapter.list.TopRatedMoviesAdapter
import com.muhammadhusniabdillah.themoviedb.ui.movielist.adapter.list.UpcomingMoviesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: MoviesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /** ADAPTER INSTANCE FOR EACH RECYCLER VIEW **/
        val popularMoviesListAdapter = PopularMoviesListAdapter { movieId ->
            findNavController().navigate(MovieListFragmentDirections.actionHomeFragmentToDetailsFragment(movieId))
        }
        val topRatedMoviesAdapter = TopRatedMoviesAdapter { movieId ->
            findNavController().navigate(MovieListFragmentDirections.actionHomeFragmentToDetailsFragment(movieId))
        }
        val upcomingMoviesAdapter = UpcomingMoviesAdapter { movieId ->
            findNavController().navigate(MovieListFragmentDirections.actionHomeFragmentToDetailsFragment(movieId))
        }

        /** OBSERVER FOR EACH MOVIES CATEGORY DATA **/
        viewModel.popularMovies.observe(viewLifecycleOwner) {
            popularMoviesListAdapter.submitData(lifecycle, it)
        }
        viewModel.topRatedMovies.observe(viewLifecycleOwner) {
            topRatedMoviesAdapter.submitData(lifecycle, it)
        }
        viewModel.upcomingMovies.observe(viewLifecycleOwner) {
            upcomingMoviesAdapter.submitData(lifecycle, it)
        }

        /** GREETING TEXT OBSERVER **/
        viewModel.name.observe(viewLifecycleOwner) {
            binding.tvWelcome.text = getString(R.string.welcome_text, it)
        }

        with(binding) {
            /** ASSIGN ADAPTER TO EACH RECYCLER VIEW **/
            recyclerPopularMovies.apply {
                layoutManager = LinearLayoutManager(
                    context,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
                adapter = popularMoviesListAdapter

            }
            recyclerTopRatedMovies.apply {
                layoutManager = LinearLayoutManager(
                    context,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
                adapter = topRatedMoviesAdapter.withLoadStateFooter(LoadingAdapter())
            }
            recyclerUpcomingMovies.apply {
                layoutManager = LinearLayoutManager(
                    context,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
                adapter = upcomingMoviesAdapter
            }

            popularMoviesListAdapter.loadStateFlow.asLiveData().observe(viewLifecycleOwner) {

            }

            /** BUTTON TO PROFILE PAGE **/
            btnProfile.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
            }
        }

    }
}