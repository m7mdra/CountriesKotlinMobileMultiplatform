import SwiftUI
import shared

struct ContentView: View {
    
    @ObservedObject var viewModel = CountryViewModel()
    var body: some View {
        NavigationView {
            switch(viewModel.state){
            case .result(let list):
                CountryListView(list: list, viewModel: viewModel)
            case .error:
                VStack{
                    Text("Failed to load data, retry")
                    Button("Retry", action: {
                        viewModel.getAll()
                    })
                }
            case .loading:
                ActivityIndicatorView(isAnimating: .constant(true), style: .medium)
                
                
            default:
                Text("Hello")
            }
        }.onAppear(perform: {
            self.viewModel.getAll()
        })
    }
    
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
struct CountryListView : View {
    let list : Array<Country>
    let viewModel:CountryViewModel
    private  let  chips = [
        Chip(isSelected: false, systemImage: "checkmark", titleKey: "Alphabatic"),
        Chip(isSelected: false, systemImage: "checkmark", titleKey: "Area"),
        Chip(isSelected: false, systemImage: "checkmark", titleKey: "Population"),
        
    ]
    var body: some View{
        ScrollView {
            ChipGroup(chips: chips) { chip in
                if(chips[0].id == chip.id){
                    viewModel.filterAlphabetic()
                }else if(chips[1].id == chip.id){
                    viewModel.filterByArea()
                }else{
                    viewModel.filterByPopulation()
                }
                
            }
            .padding(.leading,16)
            .padding(.trailing,16)
            
            VStack{
                LazyVStack {
                    ForEach(list) { country in
                        
                        NavigationLink(destination: CountryDetailsView().navigationTitle(country.name)){
                            CountryView(country: country)
                        }
                        
                    }
                    
                }.padding(.top,8)
                .clipped(antialiased: true)
            }
            
        }.navigationTitle("CountriesKMM")
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
