package com.muhammadhusniabdillah.themoviedb.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.muhammadhusniabdillah.themoviedb.R
import com.muhammadhusniabdillah.themoviedb.ui.MainActivity
import com.muhammadhusniabdillah.themoviedb.ui.authorization.AuthorizationActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TheMoviesDbInitialActivity : AppCompatActivity() {

    private val viewModel: TheMoviesDbInitialViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(R.layout.activity_tmdb_splash_screen)
        viewModel.loginState.observe(this) { isSessionExists ->
            startActivity(
                Intent(
                    this,
                    if (isSessionExists) {
                        MainActivity::class.java
                    } else {
                        AuthorizationActivity::class.java
                    }
                )
            )
            finish()
        }
    }
}
