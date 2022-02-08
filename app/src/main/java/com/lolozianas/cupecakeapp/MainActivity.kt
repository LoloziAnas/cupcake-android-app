package com.lolozianas.cupecakeapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController

/** [MainActivity] is an Activity for cupcake order flow*/
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Retrieve NavController from NavHostFragment
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        // Set up the action bar for use with the NavController
        // Show a title in the app bar based off of the destination's label,
        // and display the Up button whenever you're not on a top-level destination.
        setupActionBarWithNavController(navController)
    }

    /**
     * Handles navigation when the user chooses Up from the action bar.
     * */
    override fun navigateUpTo(upIntent: Intent?): Boolean {
        return super.navigateUpTo(upIntent) || navController.navigateUp()
    }
}