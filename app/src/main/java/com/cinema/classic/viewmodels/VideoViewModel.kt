package com.cinema.classic.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cinema.classic.data.interest.MovieClip
import com.cinema.classic.model.NaverMovie
import com.cinema.classic.repository.MovieClipRepository
import com.cinema.classic.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideoViewModel @Inject constructor(
    private val repository: MovieRepository, private val movieClipRepository: MovieClipRepository
) : ViewModel() {

    private val _data: MutableLiveData<NaverMovie> = MutableLiveData()
    val data: LiveData<NaverMovie> get() = _data
    private val movieClips: MutableLiveData<List<MovieClip>> = MutableLiveData()
    val movieClipList: LiveData<List<MovieClip>> get() = movieClips

    suspend fun getMovieDetail(title: String) = viewModelScope.launch {
        val response = repository.get(title)
        if (response.isSuccessful) {
            _data.value = response.body()?.items?.get(0)
        }
    }

    suspend fun getMovieDetail2(title: String) = viewModelScope.launch {
        val response = repository.get2(title)
        if (response.isSuccessful) {
            //_data.value = response.body()
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
}