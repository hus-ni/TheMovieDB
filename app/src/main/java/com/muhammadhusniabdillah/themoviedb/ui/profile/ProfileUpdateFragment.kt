package com.muhammadhusniabdillah.themoviedb.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.muhammadhusniabdillah.themoviedb.R
import com.muhammadhusniabdillah.themoviedb.databinding.FragmentProfileUpdateBinding
import com.muhammadhusniabdillah.themoviedb.ui.base.fragment.BaseFragment

class ProfileUpdateFragment : BaseFragment<FragmentProfileUpdateBinding>(FragmentProfileUpdateBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnUpdate.setOnClickListener{
            findNavController().navigate(R.id.action_profileUpdateFragment_to_profileFragment)
        }
    }
}