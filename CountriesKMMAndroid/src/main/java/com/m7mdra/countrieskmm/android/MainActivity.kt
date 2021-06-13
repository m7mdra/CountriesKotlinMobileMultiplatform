package com.m7mdra.countrieskmm.android

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.m7mdra.myapplication.network.CountryApi
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private val mainScope = MainScope()
    private val countryApi by lazy {
        CountryApi()
    }

    override fun onStop() {
        super.onStop()
        mainScope.coroutineContext.cancelChildren()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val progressbar = findViewById<ProgressBar>(R.id.progressBar)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        mainScope.launch {
            progressbar.visibility = View.VISIBLE
            kotlin.runCatching {
                countryApi.getAll()
            }.onSuccess {
                recyclerView.adapter  = CountryAdapter(this@MainActivity,it){
                    Log.d("MEGA", "onCreate: $it")
                }
                progressbar.visibility = View.GONE
            }.onFailure {
                progressbar.visibility = View.GONE


            }
        }

    }
}
