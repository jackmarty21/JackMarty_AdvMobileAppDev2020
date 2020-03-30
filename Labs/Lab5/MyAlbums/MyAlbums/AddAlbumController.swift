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
    
    var artist: String?
    var name: String?
    var year: String?
    
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
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        //check to make sure we're only saving when the user presses save button
        if segue.identifier == "SaveSegue" {
            //check to make sure they at least entered mileage
            artist = artistTextField.text!
            year = yearTextField.text!
            name = albumTextField.text!
        }
    }
    
}
