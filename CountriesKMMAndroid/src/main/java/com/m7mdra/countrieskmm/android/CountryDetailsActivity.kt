package com.m7mdra.countrieskmm.android

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.m7mdra.countrieskmm.data.network.model.Country
import kotlinx.android.synthetic.main.activity_country_details.*


class CountryDetailsActivity : AppCompatActivity() {
    companion object {
        @JvmStatic
        lateinit var country: Country
    }

    private val viewModel by lazy {
        ViewModelProvider.AndroidViewModelFactory.getInstance(application)
            .create(CountryDetailsViewModel::class.java)
    }


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_details)


        country.apply {

            viewModel.getBorders(borders)

            countryNameTextView.text = name
            countryNativeNameTextView.text = nativeName
            countryCapitalTextView.text = capital
            countryRegionTextView.text = "$region/$subregion"
            countryISO2TextView.text = alpha2Code
            countryISO3TextView.text = alpha3Code
            val lang = languages.map { it.name }.joinToString { "$it" }
            countryLanguagesTextView.text = lang
            countryTimezoneTextView.text = timezones.map { it }.joinToString { "$it " }
            countryCurrencyTextView.text =
                currencies.map { "${it.name} (${it.code ?: ""}) (${it.symbol ?: ""})" }.joinToString { "$it " }
            countryAreaTextView.text = "$area km"
            countryPopulationTextView.text = "$population"
        }
        viewModel.state.observe(this, { state ->
            when (state) {
                is Success -> {

                    borderRecyclerView.adapter = CountryAdapter(
                        this@CountryDetailsActivity,
                        list = state.list,
                        layoutId = R.layout.row_country_small
                    ) {
                        CountryDetailsActivity.country = it
                        startActivity(Intent(this, CountryDetailsActivity::class.java))
                    }
                }
            }

        })

    }


}