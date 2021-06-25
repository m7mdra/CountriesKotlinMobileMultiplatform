import SwiftUI
import shared

struct ContentView: View {
    private  let  chips = [
        Chip(isSelected: false, systemImage: "checkmark", titleKey: "Alphabatic"),
        Chip(isSelected: false, systemImage: "checkmark", titleKey: "Area"),
        Chip(isSelected: false, systemImage: "checkmark", titleKey: "Population"),
        
    ]
    @ObservedObject var viewModel = CountryViewModel()
    var body: some View {
        NavigationView {
            listView()
                .navigationBarTitle("CountriesKMM")
            
        }.onAppear(perform: {
            self.viewModel.getAll()
        })
    }
    private func listView() -> AnyView {
        switch viewModel.state {
        case .loading:
            return AnyView(ActivityIndicatorView(isAnimating: .constant(true), style: .medium))
        case .result(let countries):
            return AnyView(
                
                VStack{
                    ChipGroup(chips:chips ) { chip in
                        if(chips[0].id == chip.id){
                            viewModel.filterAlphabetic()
                        }else if(chips[1].id == chip.id){
                            viewModel.filterByArea()
                        }else{
                            viewModel.filterByPopulation()
                        }
                        
                    }
                    ScrollView {
                        
                        LazyVStack {
                            ForEach(countries) { country in
                                CountryView(country: country)
                            }
                            
                        }.padding(.top,8)
                        .clipped(antialiased: true)
                    }
                }
            )
        case .error(let description):
            return AnyView(Text(description).multilineTextAlignment(.center))
        case .idle:
            return AnyView(Text(""))
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

extension Country : Identifiable{
    
}
struct ActivityIndicatorView: UIViewRepresentable {
    @Binding var isAnimating: Bool
    let style: UIActivityIndicatorView.Style
    
    func makeUIView(context: UIViewRepresentableContext<ActivityIndicatorView>) -> UIActivityIndicatorView {
        return UIActivityIndicatorView(style: style)
    }
    
    func updateUIView(_ uiView: UIActivityIndicatorView, context: UIViewRepresentableContext<ActivityIndicatorView>) {
        isAnimating ? uiView.startAnimating() : uiView.stopAnimating()
    }
}
