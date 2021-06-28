package com.m7mdra.countrieskmm.android

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.m7mdra.countrieskmm.data.network.model.Country
import com.squareup.picasso.Picasso


class CountryAdapter(
    context: Context,
    @LayoutRes val layoutId: Int = R.layout.row_country,
    private val list: List<Country>,
    private val onClick: (Country) -> Unit = {}
) :
    RecyclerView.Adapter<CountryViewHolder>() {
    private val picasso: Picasso = Picasso.Builder(context)
        .loggingEnabled(true)
        .build()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder(
            LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = list[position]
        picasso.load("https://flagcdn.com/h240/${country.alpha2Code.lowercase()}.png")

            .into(holder.flagImageView)
        holder.view.setOnClickListener {
            onClick.invoke(country)
        }
        holder.nameTextView.text = country.name
        holder.nativeNameText.text = country.nativeName
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
