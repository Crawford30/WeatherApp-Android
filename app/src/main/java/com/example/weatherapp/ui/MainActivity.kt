package com.example.weatherapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.weatherapp.R
import com.example.weatherapp.database.NoteDatabase
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.repository.NoteRepository
import com.example.weatherapp.ui.notes.NoteViewModelProviderFactory
import com.example.weatherapp.ui.notes.NotesViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    private lateinit var binding: ActivityMainBinding
    lateinit var notesViewModel: NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        //set support action bar
        setSupportActionBar(toolbar)

        //Nav controller
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        //Set Bottom Navigation menu with nav controller
        bottom_nav.setupWithNavController(navController)

        //Action bar
        NavigationUI.setupActionBarWithNavController(this, navController)

        setUpViewModel()

    }

    override fun onSupportNavigateUp(): Boolean {
        // return super.onSupportNavigateUp()
        return NavigationUI.navigateUp(navController, null) //null for drawer layout
    }



    private fun setUpViewModel() {
        val noteRepository = NoteRepository(
            NoteDatabase(this)
        )

        val viewModelProviderFactory =
            NoteViewModelProviderFactory(
                application, noteRepository
            )

        notesViewModel = ViewModelProvider(
            this,
            viewModelProviderFactory
        ).get(NotesViewModel::class.java)
    }
}