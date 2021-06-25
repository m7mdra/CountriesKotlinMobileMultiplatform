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
    let country:Country
    var body: some View{
        VStack(alignment:.leading){
            Text(country.name)
                .font(.largeTitle)
        }.padding(16)
    }
}
