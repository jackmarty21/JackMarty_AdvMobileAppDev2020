//
//  CocktailDataController.swift
//  Lab4
//
//  Created by Jack Marty on 3/5/20.
//  Copyright © 2020 Jack Marty. All rights reserved.
//

import Foundation

class CocktailDataController {
    //stores all of the campsites from the most recent response
    var currentCocktails = CocktailData()
    //closure to notify the view controller when the json has been loaded and parsed
    var onDataUpdate: ((_ data: [Cocktail]) -> Void)?
    
    //makes the http request based on stateCode parameter
    func loadJson(drink: String) {
        //construct URL by interpolating the state code into
        let urlPath = "https://www.thecocktaildb.com/api/json/v1/1/search.php?s=\(drink)"

        
        //use a guard statement with conditional unwrapping to make sure the url is valid
        guard let url = URL(string: urlPath) else {
            print("bad url")
            return
        }
        
        //valid url so make the request and give it a completetion handler closure
        let session = URLSession.shared.dataTask(with: url, completionHandler: {(data, response, error) in
            //downcase to URLResponse since we made and https request
            let httpResponse = response as! HTTPURLResponse
            
            //get the status code
            let statusCode = httpResponse.statusCode
            
            //make sure we got a good response
            guard statusCode == 200 else {
                print("file download error. status code: \(statusCode)")
                return
            }
            //download successful
            print("download complete")
            //parse json asynch
            DispatchQueue.main.async {self.parseJson(rawData: data!)}
        })
        
        //must call resume to run session and execute request
        session.resume()
    }
    
    //parses the raw http response and notifies the view controller
    func parseJson(rawData: Data)  {
        do {
            //try to decode the response
            let parsedData = try JSONDecoder().decode(CocktailData.self, from: rawData)
            //clear out old data
            currentCocktails.drinks.removeAll()
            //add all the campsite entries to our class property that stores the current campsites
            for drink in parsedData.drinks {
                currentCocktails.drinks.append(drink)
            }
        } catch {
            //something went wrong parsing the data — throw error!
            print("json error")
            print(error)
            //print(error.localizedDescription)
        }
        print("parsejson done")
        
        //pass data back to requesting object
        onDataUpdate?(currentCocktails.drinks)
    }
}
