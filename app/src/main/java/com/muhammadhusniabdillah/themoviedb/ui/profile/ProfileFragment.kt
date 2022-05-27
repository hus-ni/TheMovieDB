package com.muhammadhusniabdillah.themoviedb.ui.profile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.github.dhaval2404.imagepicker.ImagePicker
import com.muhammadhusniabdillah.themoviedb.R
import com.muhammadhusniabdillah.themoviedb.data.preferences.SessionPreferences
import com.muhammadhusniabdillah.themoviedb.databinding.FragmentProfileBinding
import com.muhammadhusniabdillah.themoviedb.ui.authorization.AuthorizationActivity
import com.muhammadhusniabdillah.themoviedb.ui.base.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {


    private val viewModel: ProfileViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.picture?.observe(viewLifecycleOwner) {
            viewModel.picture?.value?.toUri().let {
                binding.profilePics.setImageURI(it)
            }
        }

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

                        btnToUpdate.setOnClickListener {
                            val toUpdatePage =
                                ProfileFragmentDirections.actionProfileFragmentToProfileUpdateFragment(
                                    data
                                )
                            findNavController().navigate(toUpdatePage)
                        }
                    }
                }
            }
        }
        binding.btnLogout.setOnClickListener {
            viewModel.loggingOut()
        }
        binding.profilePicsIcon.setOnClickListener {
            setProfilePicture()
        }
    }

    private fun setProfilePicture() {
        ImagePicker.with(requireActivity())
            .crop()
            .saveDir(File(activity?.externalCacheDir, "ProfilePics"))
            .compress(1024)
            .maxResultSize(1080, 1080)
            .createIntent {
                profilePicsResult.launch(it)
            }
    }

    private val profilePicsResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            val result = it.resultCode
            val data = it.data
            when (result) {
                Activity.RESULT_OK -> {
                    val theUri = data?.data
                    viewModel.updateUriProfilePics(theUri.toString())
                }
                ImagePicker.RESULT_ERROR -> {
                    Toast.makeText(requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT)
                        .show()
                }
                else -> {
                    //nothing
                }
            }
        }
}