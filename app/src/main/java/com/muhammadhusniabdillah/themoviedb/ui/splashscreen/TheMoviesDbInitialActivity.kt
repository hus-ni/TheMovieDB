@file:Suppress("DEPRECATION")

package com.muhammadhusniabdillah.themoviedb.ui.splashscreen

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.muhammadhusniabdillah.themoviedb.R
import com.muhammadhusniabdillah.themoviedb.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TheMoviesDbInitialActivity : AppCompatActivity() {

    private val viewModel: TheMoviesDbInitialViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(R.layout.activity_tmdb_splash_screen)
        supportActionBar?.hide()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = Color.TRANSPARENT
        window.navigationBarColor = Color.TRANSPARENT

        viewModel.loginState.observe(this){
         if (it) {
             Handler().postDelayed({
                 startActivity(Intent(this, MainActivity::class.java))
                 finish()
             }, 2000)
         } else {
             Toast.makeText(this, "Error session empty!", Toast.LENGTH_SHORT).show()
         }
        }
    }
}
