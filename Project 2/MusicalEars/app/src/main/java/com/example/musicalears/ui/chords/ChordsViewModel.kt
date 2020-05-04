package com.example.musicalears.ui.chords

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ChordsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is chords Fragment"
    }
    val text: LiveData<String> = _text
}