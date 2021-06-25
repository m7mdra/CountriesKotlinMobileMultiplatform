//
//  CountryDetailsView.swift
//  CountriesKMMiOS
//
//  Created by Sharif on 25/06/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared
struct CountryDetailsView : View {
    let country:Country = Country(alpha2Code: "AF", alpha3Code: "AFG", altSpellings: ["sudan"], area: KotlinDouble(double: 27657145), borders: ["IRN","PAK","TKM","UZB","TJK","CHN"], callingCodes: ["93"], capital: "Kabul", cioc: "AFG", currencies: [Currency(code: "AFN", name: "Afghan afghani", symbol: "؋")], demonym: "Afgan", gini: KotlinDouble(double: 27.8), languages: [Language(iso6391: "ps", iso6392: "pus", name: "Pashto", nativeName: "پښتو")], latlng: [KotlinDouble(double: 33),KotlinDouble(65)], name: "Afghanistan", nativeName: "افغانستان", numericCode: "004", population: 27657145, region: "Asia", regionalBlocs: [RegionalBloc(acronym: "SAARC", name: "South Asian Association for Regional Cooperation", otherAcronyms: [], otherNames: [])], subregion: "Southern Asia", timezones: ["UTC+04:30"], topLevelDomain: [".af"], translations: Translations(br: "Afeganistão", de: "Afghanistan", es: "Afganistán", fa: "افغانستان", fr: "Afghanistan", hr: "Afganistan", it: "Afghanistan", ja: "アフガニスタン", nl: "Afghanistan", pt: "Afeganistão"))
    var body: some View{
        VStack(alignment:.leading){
            Text("Summary")
                .font(.largeTitle)
        }.padding(16)
    }
}
struct CountryDetailsView_Previews: PreviewProvider {
    static var previews: some View {
        CountryDetailsView()
    }
}
