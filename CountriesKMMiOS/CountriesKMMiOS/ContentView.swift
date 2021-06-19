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
            return AnyView(ActivityIndicatorView(isAnimating: .constant(true), style: .medium))
        case .result(let countries):
            return AnyView(ScrollView {
                LazyVStack {
                    ForEach(countries) { country in
                        CountryView(country: country)
                    }
                }
            }.padding(.top,8)
            .clipped(antialiased: true)
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
