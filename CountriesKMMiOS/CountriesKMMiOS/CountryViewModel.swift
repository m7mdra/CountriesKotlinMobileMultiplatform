//
//  CountryViewModel.swift
//  CountriesKMMiOS
//
//  Created by Sharif on 14/06/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import shared

class CountryViewModel: ObservableObject {
    let repository = CountryRepository(countryApi: CountryApi(), database: Database(databaseDriverFactory: DatabaseDriverFactory()))
    @Published var state = State.idle
    func getAll(refresh:Bool = false){
        state = .loading
        repository.getAll(refresh:refresh) { (countires, error) in
            if let countryList = countires{
                self.state = .result(countryList)
            }else{
                self.state  = .error(error?.localizedDescription ?? "Unknown error")
            }
        }
    }
    func filterAlphabetic(){
        state = .loading
        repository.filterByAlphabetic { (countires, error) in
            if let countryList = countires{
                self.state = .result(countryList)
            }else{
                self.state  = .error(error?.localizedDescription ?? "Unknown error")
            }
        }
    }
    func filterByArea(){
        state = .loading
        repository.filterByArea { (countires, error) in
            if let countryList = countires{
                countryList.forEach { c in
                    print("\(c.name) \(c.area)")
                }
                self.state = .result(countryList)
            }else{
                self.state  = .error(error?.localizedDescription ?? "Unknown error")
            }
        }
    }
    func filterByPopulation(){
        state = .loading
        repository.filterByPopulation { (countires, error) in
            if let countryList = countires{
                self.state = .result(countryList)
            }else{
                self.state  = .error(error?.localizedDescription ?? "Unknown error")
            }
        }
    }

}
enum State {
    case idle
    case loading
    case result([Country])
    case error(String)
}
