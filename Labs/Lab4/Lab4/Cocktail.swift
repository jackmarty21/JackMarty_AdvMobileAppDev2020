//
//  Cocktail.swift
//  Lab4
//
//  Created by Jack Marty on 3/5/20.
//  Copyright © 2020 Jack Marty. All rights reserved.
//

import Foundation

struct Cocktail: Decodable {
    let strDrink: String
    let strInstructions: String
}

struct CocktailData: Decodable {
    var drinks = [Cocktail]()
}
