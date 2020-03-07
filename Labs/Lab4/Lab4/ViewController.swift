//
//  ViewController.swift
//  Lab4
//
//  Created by Jack Marty on 3/5/20.
//  Copyright © 2020 Jack Marty. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UIPickerViewDelegate, UIPickerViewDataSource {
    
    //list of states for picker
    let alcOptions = ["Tequila", "Vodka", "Gin"]
    //holds the currently selected state
    var selectedAlc = String()
    //instance of data controller
    var cocktailDC = CocktailDataController()
    //local copy of data
    var data = [Cocktail]()
    

    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        //set the intial state
        selectedAlc = alcOptions[0]
        
        //set the function to notify when response is complete
        cocktailDC.onDataUpdate = {[weak self] (data:[Cocktail]) in self?.searchDone(cocktails: data)}
    }
    
    @IBAction func searchDrinks(_ sender: Any) {
        //start loading the data
        cocktailDC.loadJson(drink: selectedAlc)
        //block user events and show spinner while fetching the campsites
        let alert = UIAlertController(title: nil, message: "Searching in \(selectedAlc)...", preferredStyle: .alert)

        let loadingIndicator = UIActivityIndicatorView(frame: CGRect(x: 0, y: 5, width: 50, height: 50))
        loadingIndicator.hidesWhenStopped = true
        loadingIndicator.style = UIActivityIndicatorView.Style.medium
        loadingIndicator.startAnimating();

        alert.view.addSubview(loadingIndicator)
        present(alert, animated: true, completion: nil)
    }
    
    //called when the json data has been parsed
    //trigger segue and set local data
    func searchDone(cocktails: [Cocktail]) {
        //dismiss the loading alery
        dismiss(animated: true, completion: nil)
        
        //set data property
        data = cocktails
        //execute the segue
        performSegue(withIdentifier: "SearchResults", sender: nil)
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        //check id of segue
        if segue.identifier == "SearchResults" {
            //downcast destination vc
            let resultsVC = segue.destination as! TableViewController
            //set the title
            resultsVC.title = "\(selectedAlc) Drinks"
            //pass the data
            resultsVC.results = data
        }
    }
    
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 1
    }

    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        return alcOptions.count
    }

    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        return alcOptions[row]
    }

    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        selectedAlc = alcOptions[row]
    }
    
    
}

