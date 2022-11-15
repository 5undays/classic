package com.cinema.classic.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    private val _text: MutableLiveData<String> = MutableLiveData()
    val text: LiveData<String> get() = _text

    fun updateText(t: String) {
        _text.postValue(t)
    }

    private val _data: MutableLiveData<NaverMovie> = MutableLiveData()
    val data: LiveData<NaverMovie> get() = _data

    fun test(t: NaverMovie) {
        _data.value = t
    }
}