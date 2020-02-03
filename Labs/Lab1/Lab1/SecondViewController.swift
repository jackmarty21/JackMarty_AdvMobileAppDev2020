//
//  SecondViewController.swift
//  Lab1
//
//  Created by Jack Marty on 2/2/20.
//  Copyright © 2020 Jack Marty. All rights reserved.
//

import UIKit

class SecondViewController: UIViewController {

    let instaScheme = "instagram://"
    let twitterScheme = "twitter://"
    let googleScheme = "http://ww.google.com/"
    
    func schemeAvailable(scheme: String) -> Bool {
        //make url from scheme
        if let url = URL(string: scheme) {
            return UIApplication.shared.canOpenURL(url)
        }
        return false
    }
    
    func openApp(scheme: String) {
        
        if let url = URL(string: scheme) {
            UIApplication.shared.open(url, options: [:], completionHandler: {
                (success) in
                print("Successfully open \(scheme)")
                //save data
                //persist user location in app
            })
        }
        
    }
    
    @IBAction func goToApp(_ sender: Any) {
        
        let instaInstalled = schemeAvailable(scheme: instaScheme)
        let twitterInstalled = schemeAvailable(scheme: twitterScheme)
        
        if instaInstalled {
            openApp(scheme: instaScheme)
        } else if twitterInstalled {
            openApp(scheme: twitterScheme)
        } else {
            openApp(scheme: googleScheme)
        }
    }
    
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }

}
