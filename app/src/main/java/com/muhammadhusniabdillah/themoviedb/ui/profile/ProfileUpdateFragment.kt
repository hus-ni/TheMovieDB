package com.muhammadhusniabdillah.themoviedb.ui.profile

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.muhammadhusniabdillah.themoviedb.R
import com.muhammadhusniabdillah.themoviedb.data.local.entity.UserTable
import com.muhammadhusniabdillah.themoviedb.databinding.FragmentProfileUpdateBinding
import com.muhammadhusniabdillah.themoviedb.ui.base.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileUpdateFragment :
    BaseFragment<FragmentProfileUpdateBinding>(FragmentProfileUpdateBinding::inflate) {

    private val viewModel: ProfileViewModel by viewModels()
    private val args: ProfileUpdateFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setEditTextValue()
        onBackPressed()

        binding.btnUpdate.setOnClickListener {
            updateProfile()
        }
    }

    private fun updateProfile() {
        val tempData = args.dataToUpdate.let {
            UserTable(
                id = it.id,
                name = binding.etUpdateName.text.toString(),
                email = it.email,
                password = binding.etUpdatePassword.text.toString(),
                profilePict = it.profilePict
            )
        }
        viewModel.updateUserData(
            newData = tempData
        )
        findNavController().navigate(R.id.action_profileUpdateFragment_to_profileFragment)
    }

    private fun setEditTextValue() {
        binding.apply {
            etUpdateName.setText(args.dataToUpdate.name)
            etUpdateEmail.setText(args.dataToUpdate.email)
            etUpdateEmail.isEnabled = false
        }
    }

    private fun onBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            AlertDialog.Builder(requireContext())
                .setTitle("Discard Changes?")
                .setMessage("Are you sure?")
                .setPositiveButton("Yes") { _, _ ->
                    findNavController().navigate(R.id.action_profileUpdateFragment_to_profileFragment)
                }
                .setNegativeButton("No") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }
}