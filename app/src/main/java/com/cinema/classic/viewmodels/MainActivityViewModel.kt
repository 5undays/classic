package com.cinema.classic.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cinema.classic.model.NaverMovie
import com.cinema.classic.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _data: MutableLiveData<NaverMovie> = MutableLiveData()
    val data: LiveData<NaverMovie> get() = _data

    fun test(t: NaverMovie) {
        _data.value = t
    }

    suspend fun getMovieDetail(title: String) = viewModelScope.launch {
        val response = repository.get(title)
        if (response.isSuccessful) {
            _data.value = response.body()?.items?.get(0)
        }
    }
}