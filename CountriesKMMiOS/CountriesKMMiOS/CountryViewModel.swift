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
    
    @Published var state = State.idle
    func getAll(){
        state = .loading
        CountryApi().getAll { (countires, error) in
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
