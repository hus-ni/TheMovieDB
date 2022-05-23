package com.muhammadhusniabdillah.themoviedb.ui.authorization.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.muhammadhusniabdillah.themoviedb.R
import com.muhammadhusniabdillah.themoviedb.databinding.FragmentLoginBinding
import com.muhammadhusniabdillah.themoviedb.ui.base.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.apply {
            getSession()
            loginSessionByEmail.observe(viewLifecycleOwner) {
                when {
                    !it.isNullOrEmpty() -> {
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                    }
                    else -> {/** no implementation until further notice **/}
                }
            }

            errorMessage.observe(viewLifecycleOwner) { error ->
                error.let {
                    binding.root.showSnackBar(error)
                }
            }

            loginState.observe(viewLifecycleOwner) {
                when {
                    it -> {
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                    }
                    else -> { /** no implementation until further notice **/ }
                }
            }
        }

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

            viewModel.userLogin(email = email, password = password)
        }
    }
}

private fun View.showSnackBar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).show()
}
