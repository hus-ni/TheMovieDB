package com.muhammadhusniabdillah.themoviedb.ui.authorization.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
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
import com.muhammadhusniabdillah.themoviedb.ui.authorization.ui.theme.Primary
import com.muhammadhusniabdillah.themoviedb.ui.authorization.ui.theme.Secondary
import com.muhammadhusniabdillah.themoviedb.ui.authorization.ui.theme.TheMovieDBTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                TheMovieDBTheme {
                    Surface(color = Primary) {
                        RegisterPage(onNavigate = { name, email, password ->
                            toLogin(
                                name,
                                email,
                                password
                            )
                        })
                    }
                }
            }
        }
    }

    private fun toLogin(name: String, email: String, password: String) {
        viewModel.getRegistrationData(name, email, password)
        findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
    }
}

@Composable
fun RegisterPage(
    onNavigate: (String, String, String) -> Unit
) {
    var nameInput by remember { mutableStateOf("") }
    var emailInput by remember { mutableStateOf("") }
    var passwordInput by remember { mutableStateOf("") }
    var passwordConfirmInput by remember { mutableStateOf("") }
    ConstraintLayout {
        val (img_TMDB_Long, registerForm) = createRefs()

        /** Image The Movies DB Long - Register **/
        Image(
            painter = painterResource(id = R.drawable.ic_long_the_movies_database),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(img_TMDB_Long) {
                    top.linkTo(parent.top)
                    start.linkTo(registerForm.start)
                    end.linkTo(registerForm.end)
                    bottom.linkTo(registerForm.top)
                }
                .padding(start = 24.dp, end = 24.dp, top = 58.dp, bottom = 8.dp),
            contentScale = ContentScale.Inside
        )

        /** Register Card Form - Register **/
        Card(
            backgroundColor = Color.White,
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .constrainAs(registerForm) {
                    top.linkTo(img_TMDB_Long.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 48.dp)
        ) {
            Column {
                /** Register Title - Register **/
                MaterialTheme {
                    Text(
                        text = stringResource(R.string.hint_btnRegister),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp, horizontal = 0.dp),
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Medium
                    )
                }

                /** Name Input TextField - Register **/
                OutlinedTextField(
                    value = nameInput,
                    onValueChange = { nameInput = it },
                    placeholder = { Text(text = stringResource(R.string.register_name_hint)) },
                    label = { Text(text = stringResource(R.string.register_name_label))},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, end= 8.dp, top = 8.dp, bottom = 4.dp),
                    shape = RoundedCornerShape(8.dp),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = "Person Name"
                        )
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Primary,
                        cursorColor = Primary,
                        textColor = Primary,
                        leadingIconColor = Primary,
                        focusedLabelColor = Primary,
                        unfocusedLabelColor = Primary,
                    )
                )

                /** Email Input TextField - Register **/
                OutlinedTextField(
                    value = emailInput,
                    onValueChange = { emailInput = it },
                    placeholder = { Text(text = stringResource(R.string.register_email_hint)) },
                    label = {Text(text = stringResource(id = R.string.label_email))},
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Email,
                            contentDescription = "Email Icon"
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, end= 8.dp, top = 4.dp, bottom = 4.dp),
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

                /** Password Input TextField - Register **/
                var isPasswordVisible by remember { mutableStateOf(false) }
                OutlinedTextField(
                    value = passwordInput,
                    onValueChange = { passwordInput = it },
                    placeholder = { Text(text = stringResource(R.string.register_password_hint)) },
                    label = {Text(text = stringResource(id = R.string.label_password))},
                    visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Lock,
                            contentDescription = "Password Icon"
                        )
                    },
                    trailingIcon = {
                        val icon =
                            if (isPasswordVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility
                        IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                            Icon(imageVector = icon, null)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, end= 8.dp, top = 4.dp, bottom = 4.dp),
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

                /** Confirm Password Input TextField - Register **/
                var isPasswordConfirmVisible by remember { mutableStateOf(false) }
                OutlinedTextField(
                    value = passwordConfirmInput,
                    onValueChange = { passwordConfirmInput = it },
                    placeholder = { Text(text = stringResource(R.string.register_passwordconfirm_hint)) },
                    label = { Text(text = stringResource(R.string.register_label_password_confirm)) },
                    visualTransformation = if (isPasswordConfirmVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Lock,
                            contentDescription = "Password Confirm Icon"
                        )
                    },
                    trailingIcon = {
                        val icon =
                            if (isPasswordConfirmVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility
                        IconButton(onClick = {
                            isPasswordConfirmVisible = !isPasswordConfirmVisible
                        }) {
                            Icon(imageVector = icon, null)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, end= 8.dp, top = 4 .dp, bottom = 8.dp),
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

                /** Register Button - Register **/
                Button(
                    onClick = {
                        onNavigate(nameInput, emailInput, passwordInput) // navigate to login back
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 8.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Secondary,
                        contentColor = Primary
                    )
                ) {
                    Text(text = stringResource(id = R.string.hint_btnRegister).uppercase())
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewRegisterPage() {
    TheMovieDBTheme {
        Surface(color = Primary) {
            RegisterPage { _, _, _ -> }
        }
    }
}