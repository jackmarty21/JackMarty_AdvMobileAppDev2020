//
//  AddAlbumController.swift
//  MyAlbums
//
//  Created by Jack Marty on 3/29/20.
//  Copyright © 2020 Jack Marty. All rights reserved.
//

import UIKit

class AddAlbumController: UIViewController, UITextFieldDelegate {

    @IBOutlet weak var artistTextField: UITextField!
    @IBOutlet weak var albumTextField: UITextField!
    @IBOutlet weak var yearTextField: UITextField!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        let tapRecognizer = UITapGestureRecognizer()
        tapRecognizer.addTarget(self, action: #selector(AddAlbumController.didTapView))
        self.view.addGestureRecognizer(tapRecognizer)
    }
    
    func textView(_ textView: UITextView, shouldChangeTextIn range: NSRange, replacementText text: String) -> Bool {
        
        if (text == "\n") {
            textView.resignFirstResponder()
            return false
        }
        return true
    }
    
    @objc func didTapView(){
        self.view.endEditing(true)
    }
    
}
