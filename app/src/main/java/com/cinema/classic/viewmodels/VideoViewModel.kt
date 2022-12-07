package com.cinema.classic.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cinema.classic.data.interest.MovieClip
import com.cinema.classic.model.NaverMovie
import com.cinema.classic.model.Plot
import com.cinema.classic.repository.MovieClipRepository
import com.cinema.classic.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class VideoViewModel @Inject constructor(
    private val repository: MovieRepository, private val movieClipRepository: MovieClipRepository
) : ViewModel() {

    private val _data: MutableLiveData<NaverMovie> = MutableLiveData()
    val data: LiveData<NaverMovie> get() = _data
    private val movieClips: MutableLiveData<List<MovieClip>> = MutableLiveData()
    val movieClipList: LiveData<List<MovieClip>> get() = movieClips

    private val _plotData: MutableLiveData<Plot> = MutableLiveData()
    val plotData: LiveData<Plot> get() = _plotData

    suspend fun getMovieDetail(title: String, year: Int) = viewModelScope.launch {
        val response = repository.get(title, year)
        if (response.isSuccessful) {
            _data.value = response.body()?.items?.get(0)
        }
    }

    suspend fun getMoviePlot(title: String) = viewModelScope.launch {
        val response = repository.get2(title)
        if (response.isSuccessful) {
            _plotData.value = response.body()?.Data?.get(0)?.Result?.get(0)?.plots?.plot?.get(0)
        }
    }

    suspend fun getMovieClips(videoId: String) {
        movieClips.value = movieClipRepository.get(videoId)
    }

    suspend fun insertMovieClip(movieClip: MovieClip) {
        movieClipRepository.insert(movieClip)
    }

    suspend fun removeMovieClip(movieClip: MovieClip) {
        movieClipRepository.delete(movieClip)
    }

    suspend fun getLastVideo() {
        //movieClipRepository.getLastVideo()
    }

}