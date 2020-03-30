//
//  AlbumDataController.swift
//  MyAlbums
//
//  Created by Jack Marty on 3/29/20.
//  Copyright © 2020 Jack Marty. All rights reserved.
//

import Foundation
import Firebase

struct Album {
    
    var artist: String
    var name: String
    var year: String
    var id: String
    
}

class AlbumDataController {
    
    var db: Firestore!
    
    var albumData = [Album]()
    
    var onDataUpdate: (([Album]) -> Void)!

    init() {
        let settings = FirestoreSettings()
        Firestore.firestore().settings = settings
        db = Firestore.firestore()
    }

    func loadData() {
        
        db.collection("runs").addSnapshotListener { querySnapshot, error in
            
            guard let collection = querySnapshot else {
                print("Error fetching collection: \(error!)")
                return
            }
            let docs = collection.documents
            
            self.albumData.removeAll()
            for doc in docs {
                let data = doc.data()
                let artist = data["artist"] as! String
                let albumName = data["name"] as! String
                let year = data["year"] as! String
                let id = doc.documentID
                let album = Album(artist: artist, name: albumName, year: year, id: id)
                self.albumData.append(album)
            }
            self.onDataUpdate(self.albumData)
            
        }
        
        
    }
}



