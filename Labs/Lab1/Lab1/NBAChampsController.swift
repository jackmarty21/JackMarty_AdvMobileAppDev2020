//
//  NBAChampsController.swift
//  Lab1
//
//  Created by Jack Marty on 2/2/20.
//  Copyright © 2020 Jack Marty. All rights reserved.
//

import Foundation

enum DataError: Error {
    case BadFilePath
    case CouldNotDecodeData
    case NoData
}

class NBAChampsController {
    
    var championshipsStruct: [NBAChamps]?
    let filename = "Tab1Data"
    
    
    func loadData() throws {
        print("Loading Data....")
        
        if let pathUrl = Bundle.main.url(forResource: filename, withExtension: "plist") {
            
            let decoder = PropertyListDecoder()
            
            do {
                let data = try Data(contentsOf: pathUrl)
                championshipsStruct = try decoder.decode([NBAChamps].self, from: data)
                print("data loaded")
                
            } catch {
                throw DataError.CouldNotDecodeData
            }
            
        } else {
            throw DataError.BadFilePath
        }
    }
    
    func getAllChamps() throws -> [String]{
        var champs = [String]()
        
        if let data = championshipsStruct {
            //we have data
            for champStruct in data {
                champs.append(champStruct.name)
            }
            return champs
            
        } else {
            throw DataError.NoData
        }
    }
    
    func getYears(idx: Int) throws -> [String] {
        if let data = championshipsStruct {
            return data[idx].championships
        } else {
            throw DataError.NoData
        }
    }
    
}
