package com.example.musicalears.ui.chords

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.musicalears.R

class ChordsFragment : Fragment() {

    private lateinit var chordsViewModel: ChordsViewModel

    //media players
    private lateinit var mpMajor: MediaPlayer
    private lateinit var mpMinor: MediaPlayer
    private lateinit var mpDiminished: MediaPlayer
    private lateinit var mpAugmented: MediaPlayer
    
    //resources
    private lateinit var playBtn: Button
    private lateinit var replayBtn: Button
    private lateinit var majorBtn: Button
    private lateinit var minorBtn: Button
    private lateinit var diminishedBtn: Button
    private lateinit var augmentedBtn: Button

    private lateinit var score: TextView

    var random = (1..4).random()
    var count = 0

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        chordsViewModel =
                ViewModelProviders.of(this).get(ChordsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_chords, container, false)

        //find resource views
        playBtn = root.findViewById(R.id.playBtn)
        replayBtn = root.findViewById(R.id.replayBtn)
        majorBtn = root.findViewById(R.id.major)
        minorBtn = root.findViewById(R.id.minor)
        diminishedBtn = root.findViewById(R.id.diminished)
        augmentedBtn = root.findViewById(R.id.augmented)
        score = root.findViewById(R.id.score)

        //create media players outlets
        mpMajor = MediaPlayer.create(context, R.raw.major)
        mpMinor = MediaPlayer.create(context, R.raw.minor)
        mpDiminished = MediaPlayer.create(context, R.raw.diminished)
        mpAugmented = MediaPlayer.create(context, R.raw.augmented)


        //create func instances
        playBtn.setOnTouchListener { _, event ->
            playSound(event)
            true
        }
        replayBtn.setOnTouchListener { _, event ->
            playSound(event)
            true
        }
        majorBtn.setOnTouchListener { _, event ->
            intervalButtonClicked(event, 1)
            true
        }
        minorBtn.setOnTouchListener { _, event ->
            intervalButtonClicked(event, 2)
            true
        }
        diminishedBtn.setOnTouchListener { _, event ->
            intervalButtonClicked(event, 3)
            true
        }
        augmentedBtn.setOnTouchListener { _, event ->
            intervalButtonClicked(event, 4)
            true
        }


        return root
    }

    private fun playSound(event: MotionEvent) {
        Log.i("InfoTag", "Random Variable: $random")
        when(random) {
            1 -> mpMajor.start()
            2 -> mpMinor.start()
            3 -> mpDiminished.start()
            4 -> mpAugmented.start()
        }
    }

    private fun intervalButtonClicked(event: MotionEvent, x: Int) {
        if (random == x)
            correctAnswer()
        else
            wrongAnswer()
    }

    private fun correctAnswer() {
        Log.i("InfoTag", "Correct")
        Toast.makeText(context, "Correct Answer", Toast.LENGTH_SHORT).show()
        count++
        score.text = "$count pts"
        random = (1..4).random()
    }

    private fun wrongAnswer() {
        Log.i("InfoTag", "Wrong")
        Toast.makeText(context, "Wrong Answer", Toast.LENGTH_SHORT).show()
    }
}
