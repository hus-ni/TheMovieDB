package com.muhammadhusniabdillah.themoviedb.ui.movielist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.muhammadhusniabdillah.themoviedb.R
import com.muhammadhusniabdillah.themoviedb.databinding.FragmentHomeBinding
import com.muhammadhusniabdillah.themoviedb.ui.base.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: MoviesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.name.observe(viewLifecycleOwner) {
            binding.tvWelcome.text = getString(R.string.welcome_text, it)
        }

        binding.apply {
            btnProfile.setOnClickListener{
                findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
            }
        }

    }
}