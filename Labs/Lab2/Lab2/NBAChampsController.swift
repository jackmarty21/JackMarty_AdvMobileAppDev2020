//
//  Continents.swift
//  countries
//
//  Created by Isaac Sheets on 2/2/20.
//  Copyright © 2020 Isaac Sheets. All rights reserved.
//

import Foundation

//need to conform to Codable protocol since we'll be encoding and decoding
struct NBAChampsDataModel: Codable {
    var name: String
    var championships: [String]
}

enum DataError: Error {
    case NoDataFile
    case CouldNotDecode
    case CouldNotEncode
}

class NBAChampsController {
    var allData = [NBAChampsDataModel]()
    let fileName = "NBAChamps"
    let dataFileName = "data.plist"
    
    //load data from plist
    func loadData() throws {
        let pathURL: URL?
        
        //get the path where our data file would be
        let dataFileURL = getDataFile(datafile: dataFileName)
        
        //check to see if the data file exists
        if FileManager.default.fileExists(atPath: dataFileURL.path) {
            pathURL = dataFileURL
        } else {
            //load default data if we can't find a user data file
            pathURL = Bundle.main.url(forResource: fileName, withExtension: "plist")
            
        }

        //check for file and get URL if possible
        if let dataURL = pathURL {
            let decoder = PropertyListDecoder()
            do {
                //get byte buffer (raw data)
                let data = try Data(contentsOf: dataURL)
                //decode to our model
                allData = try decoder.decode([NBAChampsDataModel].self, from: data)
            } catch {
                throw DataError.CouldNotDecode
            }
        }
        else {
            //couldn't get path
            throw DataError.NoDataFile
        }
    }
    
    func getDataFile(datafile: String) -> URL {
        //get path for data file
        let dirPath = FileManager.default.urls(for: .documentDirectory, in: .userDomainMask)
        let docDir = dirPath[0] //documents directory
        
        // URL for our plist
        return docDir.appendingPathComponent(datafile)
    }

    
    func writeData() throws {
        let dataFileURL = getDataFile(datafile: dataFileName)
        //get an encoder
        let encoder = PropertyListEncoder()
        //set format — plist is a type of xml
        encoder.outputFormat = .xml
        do {
            let data = try encoder.encode(allData.self)
            try data.write(to: dataFileURL)
        } catch {
            print(error)
            throw DataError.CouldNotEncode
        }
        
    }
    
    //fetch all the continents
    func getNBANames() -> [String] {
        //init empty array
        var allNBANames = [String]()
        //loop through data and append to array
        for item in allData {
            allNBANames.append(item.name)
        }
        return allNBANames
    }
    
    //get array of countries based on continent
    func getChampionships(idx: Int) -> [String] {
        return allData[idx].championships
    }
    
    //add a country
    func addChampionship(dataIdx: Int, newChamp: String, champIdx: Int) {
        allData[dataIdx].championships.insert(newChamp, at: champIdx)
    }
    
    //delete a country
    func deleteChamp(dataIdx: Int, champIdx: Int) {
        allData[dataIdx].championships.remove(at: champIdx)
    }
}
