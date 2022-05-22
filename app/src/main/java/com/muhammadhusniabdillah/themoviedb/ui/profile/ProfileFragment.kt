package com.muhammadhusniabdillah.themoviedb.ui.profile

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.muhammadhusniabdillah.themoviedb.R
import com.muhammadhusniabdillah.themoviedb.databinding.FragmentProfileBinding
import com.muhammadhusniabdillah.themoviedb.ui.base.fragment.BaseFragment

class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            btnToUpdate.setOnClickListener{
                findNavController().navigate(R.id.action_profileFragment_to_profileUpdateFragment)
            }
            btnLogout.setOnClickListener{
                findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
            }
        }
    }
}