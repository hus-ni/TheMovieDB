package com.muhammadhusniabdillah.themoviedb.ui.home

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.muhammadhusniabdillah.themoviedb.R
import com.muhammadhusniabdillah.themoviedb.databinding.FragmentHomeBinding
import com.muhammadhusniabdillah.themoviedb.ui.base.fragment.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            btnProfile.setOnClickListener{
                findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
            }
        }

    }
}