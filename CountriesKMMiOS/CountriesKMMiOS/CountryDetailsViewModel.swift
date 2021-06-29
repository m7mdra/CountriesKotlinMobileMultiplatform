//
//  CountryViewDetails.swift
//  CountriesKMMiOS
//
//  Created by Sharif on 30/06/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import shared
class CountryDetailsViewModel : ObservableObject{
    let repository = CountryRepository(countryApi: CountryApi(), database: Database(databaseDriverFactory: DatabaseDriverFactory()))
    @Published var state = State.idle
    
    func borders(ids:Array<String>){
        state = .loading
        repository.getBorderingCountries(countriesIds:ids) { (countires, error) in
            if let countryList = countires{
                print(countryList)
                self.state = .result(countryList)
            }else{
                print(error)

                self.state  = .error(error?.localizedDescription ?? "Unknown error")
            }
        }
    }
}
