//
//  ViewController.swift
//  Lab1
//
//  Created by Jack Marty on 2/2/20.
//  Copyright © 2020 Jack Marty. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UIPickerViewDataSource, UIPickerViewDelegate {
    
    let nameComp = 0
    let champComp = 1
    
    var NBAChampsCont = NBAChampsController()
    var names = [String]()
    var champs = [String]()
    
    @IBOutlet weak var picker: UIPickerView!
    @IBOutlet weak var choiceLabel: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        do {
            try NBAChampsCont.loadData()
            names = try NBAChampsCont.getAllChamps()
            champs = try NBAChampsCont.getYears(idx: 0)
        } catch {
            //handle error better
            print(error)
        }
    }
    
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 2
    }
    
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        if component == nameComp {
            return names.count
        } else if component == champComp {
            return champs.count
        } else {
            print("Unknown component")
            return -1
        }
    }
    
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        if component == nameComp {
            return names[row]
        } else if component == champComp {
            return champs[row]
        } else {
            print("Unknown component")
            return "unknown"
        }
    }
    
    //update albums and label when artist component is changed
    //update label when album component is changed
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        
        //check whihc component is changed
        if component == nameComp {
            //task 1
            do {
                champs = try NBAChampsCont.getYears(idx: row)
            } catch {
                print(error)
            }
            //reload component
            picker.reloadComponent(champComp)
            picker.selectRow(0, inComponent: champComp, animated: true)
        }
        
        let nameIdx = pickerView.selectedRow(inComponent: nameComp)
        let champIdx = pickerView.selectedRow(inComponent: champComp)
        
        choiceLabel.text = "\(names[nameIdx]) won the championship in \(champs[champIdx])"
    
    }
    
}

