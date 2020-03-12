//
//  ViewController.swift
//  MusicalEars
//
//  Created by Jack Marty on 10/7/19.
//  Copyright © 2019 Jack Marty. All rights reserved.
//

import UIKit
import AVFoundation

class ViewController: UIViewController {
    
    @IBOutlet weak var totalQuestions: UILabel!
    @IBOutlet weak var buttonPlay: UIButton!
    @IBOutlet weak var buttonReplay: UIButton!
    @IBOutlet weak var button7th: UIButton!
    @IBOutlet weak var button6th: UIButton!
    @IBOutlet weak var button5th: UIButton!
    @IBOutlet weak var button4th: UIButton!
    @IBOutlet weak var button3rd: UIButton!
    @IBOutlet weak var button2nd: UIButton!
    
    var audioPlayer = AVAudioPlayer()
    var currentFile : Int = 0
    var correctAnswers = 0
    
    
    //Picks a random wav file and plays it
    @IBAction func playSoundonTap(_ sender: UIButton) {
    
        currentFile = Int.random(in: 2 ... 7)
        playSound(fileName: String(currentFile))
        
        buttonReplay.isHidden = false
    }
    
    @IBAction func resetButton(_ sender: UIButton) {
        correctAnswers = 0
        totalQuestions.text = "\(correctAnswers) pts"
    }
    //Replay sound after user taps play button
    @IBAction func replaySound(_ sender: UIButton) {
        playSound(fileName: String(currentFile))
    }
    
    //These buttons update the score if correct or not
    @IBAction func major2nd(_ sender: UIButton) {
        updateScore(expectedVar: 2)
    }
    
    @IBAction func major3rd(_ sender: UIButton) {
        updateScore(expectedVar: 3)
    }
    
    @IBAction func perfect4th(_ sender: UIButton) {
        updateScore(expectedVar: 4)
    }
    
    @IBAction func perfect5th(_ sender: UIButton) {
        updateScore(expectedVar: 5)
    }
    
    @IBAction func major6th(_ sender: UIButton) {
        updateScore(expectedVar: 6)
    }
    
    @IBAction func major7th(_ sender: UIButton) {
        updateScore(expectedVar: 7)
    }
    
    //updates the score
    func updateScore(expectedVar : Int) {
        if currentFile == expectedVar {
            correctAnswers+=1
            totalQuestions.text = "\(correctAnswers) pts"
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
        buttonReplay.isHidden = true
        
        //Referenced from https://stackoverflow.com/questions/26961274/how-can-i-make-a-button-have-a-rounded-border-in-swift
        //**Not sure if I'm supposed to reference this or not because it is from Stack Overflow
        buttonPlay.layer.cornerRadius = 10
        button7th.layer.cornerRadius = 10
        button6th.layer.cornerRadius = 10
        button5th.layer.cornerRadius = 10
        button4th.layer.cornerRadius = 10
        button3rd.layer.cornerRadius = 10
        button2nd.layer.cornerRadius = 10
        
        buttonPlay.layer.borderWidth = 3
        button7th.layer.borderWidth = 3
        button6th.layer.borderWidth = 3
        button5th.layer.borderWidth = 3
        button4th.layer.borderWidth = 3
        button3rd.layer.borderWidth = 3
        button2nd.layer.borderWidth = 3
    
    }


}

