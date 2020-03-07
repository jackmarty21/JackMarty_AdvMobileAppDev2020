//
//  DetailViewController.swift
//  Lab4
//
//  Created by Jack Marty on 3/5/20.
//  Copyright © 2020 Jack Marty. All rights reserved.
//

import UIKit

class DetailViewController: UIViewController {

    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var instructionsLabel: UILabel!
    
    var name = String()
    var instructions = String()
    
    override func viewWillAppear(_ animated: Bool) {
        titleLabel.text = name
        instructionsLabel.text = instructions
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }


}

