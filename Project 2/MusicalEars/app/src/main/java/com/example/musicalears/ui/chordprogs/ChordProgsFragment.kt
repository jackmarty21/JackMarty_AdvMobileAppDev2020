package com.example.musicalears.ui.chordprogs

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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.musicalears.R

class ChordProgsFragment : Fragment() {

    private lateinit var chordProgsViewModel: ChordProgsViewModel

    //media players
    private lateinit var mpIii: MediaPlayer
    private lateinit var mpIIV: MediaPlayer
    private lateinit var mpIV: MediaPlayer
    private lateinit var mpIiv: MediaPlayer

    //resources
    private lateinit var playBtn: Button
    private lateinit var replayBtn: Button
    private lateinit var IiiBtn: Button
    private lateinit var IIVBtn: Button
    private lateinit var IVBtn: Button
    private lateinit var IivBtn: Button

    private lateinit var score: TextView

    var random = (1..4).random()
    var count = 0

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        chordProgsViewModel =
                ViewModelProviders.of(this).get(ChordProgsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_chordprogs, container, false)

        //find resource views
        playBtn = root.findViewById(R.id.playBtn)
        replayBtn = root.findViewById(R.id.replayBtn)
        IiiBtn = root.findViewById(R.id.major)
        IIVBtn = root.findViewById(R.id.minor)
        IVBtn = root.findViewById(R.id.diminished)
        IivBtn = root.findViewById(R.id.augmented)
        score = root.findViewById(R.id.score)

        //create media players outlets
        mpIii = MediaPlayer.create(context, R.raw.onetwo)
        mpIIV = MediaPlayer.create(context, R.raw.onefour)
        mpIV = MediaPlayer.create(context, R.raw.onefive)
        mpIiv = MediaPlayer.create(context, R.raw.onesix)


        //create func instances
        playBtn.setOnTouchListener { _, event ->
            playSound(event)
            true
        }
        replayBtn.setOnTouchListener { _, event ->
            playSound(event)
            true
        }
        IiiBtn.setOnTouchListener { _, event ->
            intervalButtonClicked(event, 1)
            true
        }
        IIVBtn.setOnTouchListener { _, event ->
            intervalButtonClicked(event, 2)
            true
        }
        IVBtn.setOnTouchListener { _, event ->
            intervalButtonClicked(event, 3)
            true
        }
        IivBtn.setOnTouchListener { _, event ->
            intervalButtonClicked(event, 4)
            true
        }

        return root
    }

    private fun playSound(event: MotionEvent) {
        Log.i("InfoTag", "Random Variable: $random")
        when(random) {
            1 -> mpIii.start()
            2 -> mpIIV.start()
            3 -> mpIV.start()
            4 -> mpIiv.start()
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
