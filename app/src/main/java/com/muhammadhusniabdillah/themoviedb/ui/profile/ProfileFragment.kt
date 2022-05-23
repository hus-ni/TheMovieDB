package com.muhammadhusniabdillah.themoviedb.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.muhammadhusniabdillah.themoviedb.R
import com.muhammadhusniabdillah.themoviedb.databinding.FragmentProfileBinding
import com.muhammadhusniabdillah.themoviedb.ui.authorization.AuthorizationActivity
import com.muhammadhusniabdillah.themoviedb.ui.base.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    private val viewModel: ProfileViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.userSession.observe(viewLifecycleOwner) {
            if (it.email.isBlank()) {
                startActivity(Intent(context, AuthorizationActivity::class.java))
                activity?.finish()
            } else {
                viewModel.userData(it.email)
                viewModel.userProfileData.observe(viewLifecycleOwner) { data ->
                    with(binding) {
                        tvEmail.setText(data.email)
                        tvName.setText(data.name)

                        btnToUpdate.setOnClickListener{
                            val toUpdatePage = ProfileFragmentDirections.actionProfileFragmentToProfileUpdateFragment(data)
                            findNavController().navigate(toUpdatePage)
                        }
                    }
                }
            }
        }

        binding.btnLogout.setOnClickListener{
                viewModel.loggingOut()
        }
    }
}