//
//  CountryMiniView.swift
//  CountriesKMMiOS
//
//  Created by Sharif on 30/06/2021.
//  Copyright © 2021 orgName. All rights reserved.
//
import shared
import Foundation
import SwiftUI
import SDWebImageSwiftUI
struct CountryMiniView : View{
    let country:Country
var body: some View{
    let font = Font.custom("", size: 20).bold()

    ZStack{
        WebImage(url: URL(string: country.flag)!)
            .resizable()
            .indicator(.activity)
            .transition(.fade(duration: 0.5))
            .aspectRatio(contentMode: .fill)
            .frame(width:200,height: 100)
        
        Rectangle()
            .foregroundColor(.black)
            
            .opacity(0.7)
            .frame(width:200,height: 100)
        VStack{
            Text(country.name)
                .lineLimit(1)
                .font(font)
                .foregroundColor(.white)
            Text(country.nativeName)
                .multilineTextAlignment(.center)
                .font(.custom("", size: 18))
                .foregroundColor(.white)
            
        }
    }
    .cornerRadius(16)
 
    .frame( width:200, height: 100)
    .fixedSize(horizontal: true, vertical: true)
    .listRowInsets(EdgeInsets())
    
}

}
