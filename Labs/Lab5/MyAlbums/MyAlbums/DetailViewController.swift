//
//  DetailViewController.swift
//  MyAlbums
//
//  Created by Jack Marty on 3/30/20.
//  Copyright © 2020 Jack Marty. All rights reserved.
//

import UIKit

class DetailViewController: UIViewController {

    @IBOutlet weak var artistLabel: UILabel!
    @IBOutlet weak var albumLabel: UILabel!
    @IBOutlet weak var yearLabel: UILabel!
    
    var album: Album?
    
    override func viewWillAppear(_ animated: Bool) {
        //check to make sure we have the run
        if let myAlbum = album {
            artistLabel.text = String(myAlbum.artist)
            albumLabel.text = String(myAlbum.name)
            yearLabel.text = String(myAlbum.year)
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */

}
