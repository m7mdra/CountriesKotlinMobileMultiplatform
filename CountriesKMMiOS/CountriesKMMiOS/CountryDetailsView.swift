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
    @ObservedObject var viewModel = CountryDetailsViewModel()
    var body: some View{
        List{
            Group{
                HStack{
                    Text("Name")
                    Spacer()
                    Text(country.name)
                }
                
                HStack{
                    Text("Native name")
                    Spacer()
                    Text(country.nativeName)
                }
                
                HStack{
                    Text("Capital")
                    Spacer()
                    Text(country.capital)
                }
                HStack{
                    Text("Reigon")
                    Spacer()
                    Text("\(country.region)/\(country.subregion)")
                }
                
                HStack{
                    Text("ISO 2 code")
                    Spacer()
                    Text(country.alpha2Code)
                }
                HStack{
                    Text("ISO 3 code")
                    Spacer()
                    Text(country.alpha3Code)
                }
                HStack{
                    Text("Language")
                    Spacer()
                    Text(ListFormatter.localizedString(byJoining: country.languages.map{$0.name ?? ""}))
                }
                HStack{
                    Text("Timezone")
                    Spacer()
                    Text(ListFormatter.localizedString(byJoining: country.timezones))
                }
                
                HStack{
                    Text("Currencies")
                    Spacer()
                    Text(ListFormatter.localizedString(byJoining: country.currencies.map{$0.name ?? ""}))
                }
                HStack{
                    Text("Area")
                    Spacer()
                    Text("\(country.area ?? 0)")
                }
                
            }
            Group{
                HStack{
                    Text("Population")
                    Spacer()
                    Text("\(country.population )")
                }
                Text("Borders")
                    .padding(.top)
                    .font(/*@START_MENU_TOKEN@*/.title/*@END_MENU_TOKEN@*/)
                
                switch(viewModel.state){
                case .result(let list):
                    ScrollView(.horizontal){
                        LazyHStack{
                            ForEach(list){ country in
                               NavigationLink(
                                destination: CountryDetailsView(country: country),
                                label: {
                                    CountryMiniView(country: country)
                                })
                                
                            }
                        }
                    }
                case .error:
                    VStack{
                        Text("Failed to load data, retry")
                        Button("Retry", action: {
                            viewModel.borders(ids: country.borders)
                        })
                    }
                case .loading:
                    ActivityIndicatorView(isAnimating: .constant(true), style: .medium)
                    
                    
                default:
                    Text("Hello")
                }
            }.onAppear(perform: {
                viewModel.borders(ids: country.borders)
            })

        }
    }
}
