package com.example.musicalears.ui.chordprogs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ChordProgsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is chordsprogs Fragment"
    }
    val text: LiveData<String> = _text
}