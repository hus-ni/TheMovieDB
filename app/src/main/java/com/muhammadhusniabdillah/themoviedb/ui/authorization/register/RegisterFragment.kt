package com.muhammadhusniabdillah.themoviedb.ui.authorization.register

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.muhammadhusniabdillah.themoviedb.R
import com.muhammadhusniabdillah.themoviedb.databinding.FragmentRegisterBinding
import com.muhammadhusniabdillah.themoviedb.ui.base.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val viewModel: RegisterViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegister.setOnClickListener {
            toLogin()
        }
    }

    private fun toLogin() {
        putData()
        findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
    }

    private fun putData() {
        viewModel.getRegistrationData(
            binding.etRegisterName.text.toString(),
            binding.etRegisterEmail.text.toString(),
            binding.etRegisterPassword.text.toString()
        )
    }

}

//private val viewModel: RegisterViewModel by lazy {
//        val activity = requireNotNull(this.activity) {
//            "You can only access the viewModel after onActivityCreated()"
//        }
//        ViewModelProvider(
//            this,
//            RegisterViewModel.Factory(activity.application)
//        )[RegisterViewModel::class.java]
//    }