package com.muhammadhusniabdillah.themoviedb.ui.authorization.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.muhammadhusniabdillah.themoviedb.R
import com.muhammadhusniabdillah.themoviedb.databinding.FragmentLoginBinding
import com.muhammadhusniabdillah.themoviedb.ui.MainActivity
import com.muhammadhusniabdillah.themoviedb.ui.showSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModels()
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.apply {
            tvRegister.setContent {
                TextViewRegister()
            }
            etEmailPassword.setContent {
                TextInputEmail()
            }
        }
        return binding.root
    }

    @Composable
    private fun TextInputEmail() {
        var emailInput by remember { mutableStateOf(TextFieldValue("")) }
        var passwordInput by remember { mutableStateOf(TextFieldValue("")) }
        Column {
            OutlinedTextField(
                value = emailInput,
                onValueChange = { emailInput = it },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Email Icon"
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                label = { Text(text = getString(R.string.label_email)) },
                placeholder = { Text(text = getString(R.string.hint_email)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 0.dp, top = 16.dp, bottom = 4.dp, end = 0.dp),
                shape = RoundedCornerShape(8.dp)
            )
            OutlinedTextField(
                value = passwordInput,
                onValueChange = { passwordInput = it },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Password Icon"
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                label = { Text(text = getString(R.string.label_password)) },
                placeholder = { Text(text = getString(R.string.hint_password)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 0.dp, top = 4.dp, bottom = 8.dp, end = 0.dp),
                shape = RoundedCornerShape(8.dp)
            )
            MaterialTheme {
                Button(
                    onClick = {
                        viewModel.userLogin(
                            email = emailInput.toString(),
                            password = passwordInput.toString()
                        )
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = colorResource(id = R.color.secondary_background)
                    ),
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 0.dp, top = 8.dp, bottom = 8.dp, end = 0.dp)
                ) {
                    Text(
                        text = getString(R.string.hint_btnLogin),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }

    @Composable
    private fun TextViewRegister() {
        MaterialTheme {
            Text(
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                text = getString(R.string.register_text_loginPage),
                modifier = Modifier
                    .clickable { findNavController().navigate(R.id.action_loginFragment_to_registerFragment) }
                    .focusable(true),
                fontWeight = FontWeight.SemiBold
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loginSessionByEmail.observe(viewLifecycleOwner) { isLoginSuccess ->
            if (isLoginSuccess) {
                val intent = Intent(context, MainActivity::class.java)
                startActivity(intent)
                activity?.finish()
            }
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { error ->
            error.let {
                binding.root.showSnackBar(error)
            }
        }
    }
}
