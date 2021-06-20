package com.m7mdra.countrieskmm.android

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    private val viewModel by lazy {
        ViewModelProvider.AndroidViewModelFactory.getInstance(application)
            .create(CountryViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val progressbar = findViewById<ProgressBar>(R.id.progressBar)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        viewModel.loadCountries()
        /*
        mainScope.launch {
            kotlin.runCatching {
                repository.getAll()
            }.onSuccess {

            }.onFailure {
                it.printStackTrace()
                progressbar.visibility = View.GONE*/
        viewModel.state.observe(this) { state ->
            when (state) {
                ErrorState -> {
                    progressbar.visibility = View.GONE
                }
                LoadingState -> {
                    progressbar.visibility = View.VISIBLE

                }

                is Success -> {
                    progressbar.visibility = View.GONE
                    recyclerView.adapter = CountryAdapter(this@MainActivity, state.list) {
                        Log.d("MEGA", "onCreate: $it")
                    }
                }
            }
        }
    }


    fun load() {

    }
}
