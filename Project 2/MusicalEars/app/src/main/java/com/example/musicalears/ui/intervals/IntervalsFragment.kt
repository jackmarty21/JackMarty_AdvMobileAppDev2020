package com.example.musicalears.ui.intervals

import android.media.MediaPlayer
import android.os.Bundle
import android.provider.MediaStore
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
import kotlinx.android.synthetic.main.fragment_intervals.*

class IntervalsFragment : Fragment() {

    //media players
    private lateinit var mPm2: MediaPlayer
    private lateinit var mPM2: MediaPlayer
    private lateinit var mPm3: MediaPlayer
    private lateinit var mPM3: MediaPlayer
    private lateinit var mPp4: MediaPlayer
    private lateinit var mPtr: MediaPlayer
    private lateinit var mPp5: MediaPlayer
    private lateinit var mPm6: MediaPlayer
    private lateinit var mPM6: MediaPlayer
    private lateinit var mPm7: MediaPlayer
    private lateinit var mPM7: MediaPlayer
    private lateinit var mPp8: MediaPlayer

    private lateinit var homeViewModel: IntervalsViewModel

    //resources
    private lateinit var playBtn: Button
    private lateinit var replayBtn: Button
    private lateinit var m2Btn: Button
    private lateinit var M2Btn: Button
    private lateinit var m3Btn: Button
    private lateinit var M3Btn: Button
    private lateinit var p4Btn: Button
    private lateinit var triBtn: Button
    private lateinit var p5Btn: Button
    private lateinit var m6Btn: Button
    private lateinit var M6Btn: Button
    private lateinit var m7Btn: Button
    private lateinit var M7Btn: Button
    private lateinit var p8Btn: Button
    private lateinit var score: TextView

    var random = (1..12).random()
    var count = 0

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProviders.of(this).get(IntervalsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_intervals, container, false)
        
        //find resource views
        playBtn = root.findViewById(R.id.playBtn)
        replayBtn = root.findViewById(R.id.replayBtn)
        m2Btn = root.findViewById(R.id.m2Btn)
        M2Btn = root.findViewById(R.id.M2Btn)
        m3Btn = root.findViewById(R.id.m3Btn)
        M3Btn = root.findViewById(R.id.M3Btn)
        p4Btn = root.findViewById(R.id.p4Btn)
        triBtn = root.findViewById(R.id.triBtn)
        p5Btn = root.findViewById(R.id.p5Btn)
        m6Btn = root.findViewById(R.id.m6Btn)
        M6Btn = root.findViewById(R.id.M6Btn)
        m7Btn = root.findViewById(R.id.m7Btn)
        M7Btn = root.findViewById(R.id.M7Btn)
        p8Btn = root.findViewById(R.id.p8Btn)
        score = root.findViewById(R.id.score)

        //create media players outlets
        mPm2 = MediaPlayer.create(context, R.raw.minor2)
        mPM2 = MediaPlayer.create(context, R.raw.major2)
        mPm3 = MediaPlayer.create(context, R.raw.minor3)
        mPM3 = MediaPlayer.create(context, R.raw.major3)
        mPp4 = MediaPlayer.create(context, R.raw.perfect4)
        mPtr = MediaPlayer.create(context, R.raw.tritone)
        mPp5 = MediaPlayer.create(context, R.raw.perfect5)
        mPm6 = MediaPlayer.create(context, R.raw.minor6)
        mPM6 = MediaPlayer.create(context, R.raw.major6)
        mPm7 = MediaPlayer.create(context, R.raw.minor7)
        mPM7 = MediaPlayer.create(context, R.raw.major7)
        mPp8 = MediaPlayer.create(context, R.raw.octave)

        //create func instances
        playBtn.setOnTouchListener { _, event ->
            playSound(event)
            true
        }
        replayBtn.setOnTouchListener { _, event ->
            playSound(event)
            true
        }
        m2Btn.setOnTouchListener { _, event ->
            intervalButtonClicked(event, 1)
            true
        }
        M2Btn.setOnTouchListener { _, event ->
            intervalButtonClicked(event, 2)
            true
        }
        m3Btn.setOnTouchListener { _, event ->
            intervalButtonClicked(event, 3)
            true
        }
        M3Btn.setOnTouchListener { _, event ->
            intervalButtonClicked(event, 4)
            true
        }
        p4Btn.setOnTouchListener { _, event ->
            intervalButtonClicked(event, 5)
            true
        }
        triBtn.setOnTouchListener { _, event ->
            intervalButtonClicked(event, 6)
            true
        }
        p5Btn.setOnTouchListener { _, event ->
            intervalButtonClicked(event, 7)
            true
        }
        m6Btn.setOnTouchListener { _, event ->
            intervalButtonClicked(event, 8)
            true
        }
        M6Btn.setOnTouchListener { _, event ->
            intervalButtonClicked(event,9 )
            true
        }
        m7Btn.setOnTouchListener { _, event ->
            intervalButtonClicked(event, 10)
            true
        }
        M7Btn.setOnTouchListener { _, event ->
            intervalButtonClicked(event, 11)
            true
        }
        p8Btn.setOnTouchListener { _, event ->
            intervalButtonClicked(event, 12)
            true
        }


//        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })

        return root
    }

    private fun playSound(event: MotionEvent) {
        Log.i("InfoTag", "Random Variable: $random")
        when(random) {
            1 -> mPm2.start()
            2 -> mPM2.start()
            3 -> mPm3.start()
            4 -> mPM3.start()
            5 -> mPp4.start()
            6 -> mPtr.start()
            7 -> mPp5.start()
            8 -> mPm6.start()
            9 -> mPM6.start()
            10 -> mPm7.start()
            11 -> mPM7.start()
            12 -> mPp8.start()
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
        random = (1..12).random()
    }

    private fun wrongAnswer() {
        Log.i("InfoTag", "Wrong")
        Toast.makeText(context, "Wrong Answer", Toast.LENGTH_SHORT).show()
    }
}
