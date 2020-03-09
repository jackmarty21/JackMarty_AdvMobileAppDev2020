//
//  ViewController.swift
//  MusicalEars
//
//  Created by Jack Marty on 10/7/19.
//  Copyright © 2019 Jack Marty. All rights reserved.
//

import UIKit
import AVFoundation

class ChordProgController: UIViewController {
    
    
    @IBOutlet weak var playButton: UIButton!
    @IBOutlet weak var scoreLabel: UILabel!
    @IBOutlet weak var replayButton: UIButton!
    @IBOutlet weak var prog1Button: UIButton!
    @IBOutlet weak var prog2Button: UIButton!
    @IBOutlet weak var prog3Button: UIButton!
    @IBOutlet weak var prog4Button: UIButton!
    
    
    var audioPlayer = AVAudioPlayer()
    var currentFile : Int = 0
    var correctAnswers = 0
    
    @IBAction func playSound(_ sender: Any) {
        currentFile = Int.random(in: 12 ... 15)
        playSound(fileName: String(currentFile))
        
        replayButton.isHidden = false
    }
    @IBAction func replaySound(_ sender: Any) {
        playSound(fileName: String(currentFile))
    }
    
    
    @IBAction func prog1ButtonClicked(_ sender: Any) {
        updateScore(expectedVar: 12)
    }
    @IBAction func prog2ButtonClicked(_ sender: Any) {
        updateScore(expectedVar: 13)
    }
    @IBAction func prog3ButtonClicked(_ sender: Any) {
        updateScore(expectedVar: 14)
    }
    @IBAction func prog4ButtonClicked(_ sender: Any) {
        updateScore(expectedVar: 15)
    }

    
    //updates the score
    func updateScore(expectedVar : Int) {
        if currentFile == expectedVar {
            correctAnswers+=1
            scoreLabel.text = "\(correctAnswers) pts"
        }
    }
    
    //Code to play sound
    //Referenced code from https://gist.github.com/cliff538/91b8f8bf818d836e1d9537081d02c580
    func playSound(fileName : String) {
        let sound = Bundle.main.url(forResource: fileName, withExtension: "wav")
        
        do {
            audioPlayer = try AVAudioPlayer(contentsOf: sound!)
        }
        catch {
            print(error)
        }
        
        audioPlayer.play()
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        replayButton.isHidden = true
        
        //Referenced from https://stackoverflow.com/questions/26961274/how-can-i-make-a-button-have-a-rounded-border-in-swift
        //**Not sure if I'm supposed to reference this or not because it is from Stack Overflow
        playButton.layer.cornerRadius = 10
        prog1Button.layer.cornerRadius = 10
        prog2Button.layer.cornerRadius = 10
        prog3Button.layer.cornerRadius = 10
        prog4Button.layer.cornerRadius = 10
        
        playButton.layer.borderWidth = 3
        prog1Button.layer.borderWidth = 3
        prog2Button.layer.borderWidth = 3
        prog3Button.layer.borderWidth = 3
        prog4Button.layer.borderWidth = 3
    
    }
}

