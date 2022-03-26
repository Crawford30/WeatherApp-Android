package com.example.weatherapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.weatherapp.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //set support action bar
        setSupportActionBar(toolbar)

        //Nav controller
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        //Set Bottom Navigation menu with nav controller
        bottom_nav.setupWithNavController(navController)

        //Action bar
        NavigationUI.setupActionBarWithNavController(this, navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        // return super.onSupportNavigateUp()
        return NavigationUI.navigateUp(navController, null) //null for drawer layout
    }
}