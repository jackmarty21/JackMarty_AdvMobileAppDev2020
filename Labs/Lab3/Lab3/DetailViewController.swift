//
//  DetailViewController.swift
//  Lab3
//
//  Created by Jack Marty on 3/1/20.
//  Copyright © 2020 Jack Marty. All rights reserved.
//

import UIKit

class DetailViewController: UIViewController {

    var imageName : String?
    
    @IBOutlet weak var imageView: UIImageView!
    
    @IBAction func share(_ sender: Any) {
        var imageArray = [UIImage]()
        
        imageArray.append(imageView.image!)
        
        let shareScreen = UIActivityViewController(activityItems: imageArray, applicationActivities: nil)
        
        shareScreen.modalPresentationStyle = .popover
        present(shareScreen, animated: true, completion: nil)
    }
    
    override func viewWillAppear(_ animated: Bool) {
        if let name = imageName {
            imageView.image = UIImage(named: name)
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
