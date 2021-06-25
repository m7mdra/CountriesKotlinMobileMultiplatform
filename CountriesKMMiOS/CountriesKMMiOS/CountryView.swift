//
//  CountryView.swift
//  pixus
//
//  Created by Sharif on 13/06/2021.
//

import Foundation
import SwiftUI
import shared
import SDWebImageSwiftUI
struct CountryView :View {
    var isFirst = false
    let country:Country
    let font = Font.custom("", size: 30).bold()
    let onTap:(Country) -> Void
    var body: some View{
        ZStack{
            WebImage(url: URL(string: country.flag)!)
                .resizable()
                .indicator(.activity)
                .transition(.fade(duration: 0.5))
                .aspectRatio(contentMode: .fill)
                .frame(height: 150)
            Rectangle()
                .foregroundColor(.black)
                
                .opacity(0.7)
                .frame(height: 150)
            VStack{
                Text(country.name)
                .multilineTextAlignment(.center)
                .font(font)
                .foregroundColor(.white)
                Text(country.nativeName)
                    .multilineTextAlignment(.center)
                    .font(.custom("", size: 18))
                    .foregroundColor(.white)
                    
            }
        }
        .cornerRadius(16)
        .padding(.top, isFirst ? 8 : 0)
        .padding(.leading,16)
        .padding(.trailing,16)
        .padding(.bottom,8)
        .frame( height: 150)
        .fixedSize(horizontal: false, vertical: true)
        .listRowInsets(EdgeInsets())
        .onTapGesture {
            onTap(country)
        }
    }
}
