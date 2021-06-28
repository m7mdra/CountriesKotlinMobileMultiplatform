package com.m7mdra.countrieskmm.android

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.ChipGroup


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
        val chipGroup = findViewById<ChipGroup>(R.id.chipGroup)
        chipGroup.isSingleSelection = true
        chipGroup.setOnCheckedChangeListener { _, checkedId ->
            Log.d("MEGA", "checked$checkedId")
            when (checkedId) {
                R.id.alphaChip -> {
                    viewModel.filter(0)
                }
                R.id.populationChip -> {
                    viewModel.filter(1)

                }
                R.id.sizeChip -> {
                    viewModel.filter(2)

                }
            }
        }
        viewModel.loadCountries()

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
                    chipGroup.visibility = View.VISIBLE
                    recyclerView.adapter = CountryAdapter(this@MainActivity, list = state.list) {
                        CountryDetailsActivity.country = it
                        startActivity(Intent(this, CountryDetailsActivity::class.java))
                    }
                }
            }
        }
    }


}
