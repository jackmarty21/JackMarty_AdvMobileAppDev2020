//
//  ViewController.swift
//  MusicalEars
//
//  Created by Jack Marty on 10/7/19.
//  Copyright © 2019 Jack Marty. All rights reserved.
//

import UIKit
import AVFoundation

class ChordTypesController: UIViewController {
    
    @IBOutlet weak var playButton: UIButton!
    @IBOutlet weak var replayButton: UIButton!
    @IBOutlet weak var majorButton: UIButton!
    @IBOutlet weak var minorButton: UIButton!
    @IBOutlet weak var dimButton: UIButton!
    @IBOutlet weak var augButton: UIButton!
    @IBOutlet weak var scoreLabel: UILabel!
    
    
    var audioPlayer = AVAudioPlayer()
    var currentFile : Int = 0
    var correctAnswers = 0
    
    @IBAction func resetButton(_ sender: UIButton) {
        correctAnswers = 0
        scoreLabel.text = "\(correctAnswers) pts"
    }
    @IBAction func playSound(_ sender: Any) {
        currentFile = Int.random(in: 8 ... 11)
        playSound(fileName: String(currentFile))
        
        replayButton.isHidden = false
    }
    @IBAction func replaySound(_ sender: Any) {
        playSound(fileName: String(currentFile))
    }
    
    @IBAction func majorButtonClicked(_ sender: Any) {
        updateScore(expectedVar: 8)
    }
    @IBAction func minorButtonClicked(_ sender: Any) {
        updateScore(expectedVar: 9)
    }
    @IBAction func dimButtonClicked(_ sender: Any) {
        updateScore(expectedVar: 10)
    }
    @IBAction func augButtonClicked(_ sender: Any) {
        updateScore(expectedVar: 11)
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
        majorButton.layer.cornerRadius = 10
        minorButton.layer.cornerRadius = 10
        dimButton.layer.cornerRadius = 10
        augButton.layer.cornerRadius = 10
        
        playButton.layer.borderWidth = 3
        majorButton.layer.borderWidth = 3
        minorButton.layer.borderWidth = 3
        dimButton.layer.borderWidth = 3
        augButton.layer.borderWidth = 3
    
    }
}

