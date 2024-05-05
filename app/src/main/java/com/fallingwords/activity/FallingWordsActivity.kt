package com.fallingwords.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.fallingwords.R
import com.fallingwords.databinding.ActivityFallingWordsBinding
import dagger.hilt.android.AndroidEntryPoint

/** This activity is used to navigate between screens using fragments **/
@AndroidEntryPoint
class FallingWordsActivity : AppCompatActivity() {

    /** This variable will store the instance of FallingWordsActivity layout file views **/
    private lateinit var binding: ActivityFallingWordsBinding

    /** This function will store the instance of NavHostFragment **/
    private lateinit var navHostFragment: NavHostFragment

    /** This function will store the instance of NavController **/
    private lateinit var navController: NavController

    /** This function is used to initialize the process of the screen **/
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityFallingWordsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    /** This function will start calling the functions to initialize views **/
    private fun initViews() {
        setNavigationViews()
    }

    /** This function will initialize the navController and navHost **/
    private fun setNavigationViews() {
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.navController
    }

}