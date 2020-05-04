package com.example.musicalears.ui.intervals

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class IntervalsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is intervals Fragment"
    }
    val text: LiveData<String> = _text
}