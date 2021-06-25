//
//  ChipView.swift
//  CountriesKMMiOS
//
//  Created by Sharif on 25/06/2021.
//  Copyright © 2021 orgName. All rights reserved.
//


import SwiftUI

struct Chip: Identifiable {
    let id = UUID()
    var isSelected: Bool
    let systemImage: String
    let titleKey: LocalizedStringKey
}




extension Chip{
    func isSelected(id:String)->Bool{
        return  self.id.uuidString == id
    }
}
struct ChipGroup: View {
    @SwiftUI.State var selectedChipId : String = ""
    var chips : Array<Chip>
    var onSelect: (Chip)->Void
    
    var body: some View {
        ScrollView(.horizontal, showsIndicators: false){
            HStack( content: {
                ForEach(chips) { chipsData in
                    Chips(systemImage: chipsData.isSelected(id: selectedChipId) ? chipsData.systemImage : "",
                          titleKey: chipsData.titleKey,
                          isSelected: chipsData.isSelected(id: selectedChipId),onTap: {
                            onSelect(chipsData)
                            withAnimation {
                                selectedChipId = chipsData.id.uuidString
                            }
                          })
                    
                    
                }
            })
        }
    }
}


struct Chips: View {
    let systemImage: String
    let titleKey: LocalizedStringKey
    var isSelected: Bool
    var onTap: ()->Void
    let color = UIColor(red: 0.92, green: 0.92, blue: 0.92, alpha: 1.00)
    let selectedColor = UIColor(red: 0.83, green: 0.83, blue: 0.83, alpha: 1.00)
    var body: some View {
        HStack {
            Image(systemName: systemImage)
            Text(titleKey).font(.body).lineLimit(1)
        }.padding(.leading, 8)
        .padding(.trailing,8)
        .padding(.top,2)
        .padding(.bottom,2)
        .foregroundColor(isSelected ? .white : .blue)
        .background(isSelected ? Color(selectedColor.cgColor) : Color(color.cgColor))
        .cornerRadius(40)
        .onTapGesture {
            onTap()
        }
    }
}
