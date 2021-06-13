import SwiftUI
import shared

struct ContentView: View {

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
            return AnyView(Text("Loading...").multilineTextAlignment(.center))
        case .result(let countries):
            return AnyView(List(countries){country in
                return CountryView(country: country)
            })
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
