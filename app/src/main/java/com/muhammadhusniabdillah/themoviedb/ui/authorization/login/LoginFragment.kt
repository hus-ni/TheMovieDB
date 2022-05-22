package com.muhammadhusniabdillah.themoviedb.ui.authorization.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.muhammadhusniabdillah.themoviedb.R
import com.muhammadhusniabdillah.themoviedb.databinding.FragmentLoginBinding
import com.muhammadhusniabdillah.themoviedb.ui.authorization.register.RegisterViewModel
import com.muhammadhusniabdillah.themoviedb.ui.base.fragment.BaseFragment

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            validateUserLogin()
        }
        binding.tvRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

    }

    private fun validateUserLogin() {
        with(binding) {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            viewModel.userLogin(email, password)
        }
    }

}