//
//  ThirdViewController.swift
//  Lab1
//
//  Created by Jack Marty on 2/2/20.
//  Copyright © 2020 Jack Marty. All rights reserved.
//

import UIKit
import AVFoundation

class ThirdViewController: UIViewController {
    
    var audioPlayer = AVAudioPlayer()
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }

    @IBAction func playLebron(_ sender: Any) {
        
        playSound(fileName: "lebron")
        
    }
    
    @IBAction func playKobe(_ sender: Any) {
        
        playSound(fileName: "kobe")
        
    }
    
    @IBAction func playMJ(_ sender: Any) {
        
        playSound(fileName: "mj")
        
    }
    
    func playSound(fileName: String) {
        
        let sound = Bundle.main.url(forResource: fileName, withExtension: "mp3")
        
        do {
            audioPlayer = try AVAudioPlayer(contentsOf: sound!)
        }
        catch {
            print(error)
        }
        audioPlayer.play()
        
    }
    
}
