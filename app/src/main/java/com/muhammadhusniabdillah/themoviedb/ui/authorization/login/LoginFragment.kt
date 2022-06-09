package com.muhammadhusniabdillah.themoviedb.ui.authorization.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.muhammadhusniabdillah.themoviedb.R
import com.muhammadhusniabdillah.themoviedb.ui.MainActivity
import com.muhammadhusniabdillah.themoviedb.ui.authorization.ui.theme.Primary
import com.muhammadhusniabdillah.themoviedb.ui.authorization.ui.theme.TheMovieDBTheme
import com.muhammadhusniabdillah.themoviedb.ui.showSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                TheMovieDBTheme {
                    Surface(color = Primary) {
                        LoginPage(
                            login = { email, password ->
                                login(
                                    emailInput = email,
                                    passwordInput = password
                                )
                            },
                            toRegister = { navigateRegister() })
                    }
                }
            }
        }
    }

    private fun login(emailInput: String, passwordInput: String) {
        viewModel.userLogin(
            email = emailInput,
            password = passwordInput
        )
    }

    private fun navigateRegister() {
        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
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
                view.showSnackBar(it)
            }
        }
    }
}

@Composable
fun LoginPage(
    login: (String, String) -> Unit,
    toRegister: () -> Unit
) {
    var emailInput by remember { mutableStateOf("") }
    var passwordInput by remember { mutableStateOf("") }
    ConstraintLayout {
        val (img_TMDB_Login, loginForm) = createRefs()
        Image(          // The Movies DB Image Login Page
            painter = painterResource(id = R.drawable.ic_login_logo_the_movies_database),
            contentDescription = null,
            alignment = Alignment.Center,
            modifier = Modifier
                .constrainAs(img_TMDB_Login) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(loginForm.top)
                }
                .padding(top = 64.dp)
        )
        Card(
            backgroundColor = Color.White,
            modifier = Modifier
                .constrainAs(loginForm) {
                    top.linkTo(img_TMDB_Login.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(start = 16.dp, end = 16.dp, top = 64.dp)
        ) {
            Column {
                /** Login Email Input Text Field. - Login Page **/
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
                    label = { Text(text = stringResource(R.string.label_email)) },
                    placeholder = { Text(text = stringResource(R.string.hint_email)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, top = 16.dp, bottom = 4.dp, end = 8.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Primary,
                        cursorColor = Primary,
                        textColor = Primary,
                        leadingIconColor = Primary,
                        focusedLabelColor = Primary,
                        unfocusedLabelColor = Primary
                    )
                )
                /** Login Password Input Text Field. - Login Page **/
                var isPasswordVisible by remember { mutableStateOf(false) }
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
                    visualTransformation =
                    if (isPasswordVisible) VisualTransformation.None
                    else PasswordVisualTransformation(),
                    trailingIcon = {
                        val icon =
                            if (isPasswordVisible) Icons.Filled.VisibilityOff
                            else Icons.Filled.Visibility
                        IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                            Icon(imageVector = icon, null)
                        }
                    },
                    label = { Text(text = stringResource(R.string.label_password)) },
                    placeholder = { Text(text = stringResource(R.string.hint_password)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, top = 4.dp, bottom = 8.dp, end = 8.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Primary,
                        cursorColor = Primary,
                        textColor = Primary,
                        leadingIconColor = Primary,
                        trailingIconColor = Primary,
                        focusedLabelColor = Primary,
                        unfocusedLabelColor = Primary
                    )
                )
                MaterialTheme {
                    /** Login Button - Login Page **/
                    Button(
                        onClick = { login(emailInput, passwordInput) },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = colorResource(id = R.color.secondary_background)
                        ),
                        shape = RoundedCornerShape(15.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp, top = 8.dp, bottom = 8.dp, end = 8.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.hint_btnLogin),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    /** Clickable Text View to Register Page. - Login Page **/
                    Text(
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp,
                        text = stringResource(R.string.register_text_loginPage),
                        modifier = Modifier
                            .clickable(onClick = { toRegister() }, enabled = true)
                            .focusable(true)
                            .fillMaxWidth()
                            .padding(top = 8.dp, bottom = 16.dp),
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginPagePreview() {
    TheMovieDBTheme {
        Surface(color = Primary) {
            LoginPage({ _, _ -> }, {})
        }
    }
}
