package com.cinema.classic.viewmodels

import androidx.lifecycle.*
import com.cinema.classic.data.MovieClip
import com.cinema.classic.model.NaverMovie
import com.cinema.classic.model.Plot
import com.cinema.classic.data.MovieClipRepository
import com.cinema.classic.data.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideoViewModel @Inject constructor(
    private val repository: MovieRepository,
    private val movieClipRepository: MovieClipRepository
) : ViewModel() {
    private val videoId = MutableLiveData("videoId")
    val video_id: LiveData<String>
        get() = videoId

    private val _data: MutableLiveData<NaverMovie> = MutableLiveData()
    val data: LiveData<NaverMovie> get() = _data
    val movieClipList: LiveData<List<MovieClip>> get() = movieClipRepository.get(videoId.value!!).asLiveData()

    private val _plotData: MutableLiveData<Plot> = MutableLiveData()
    val plotData: LiveData<Plot> get() = _plotData

    suspend fun getMovieDetail(title: String, year: Int, video_id: String) = viewModelScope.launch {
        videoId.value = video_id
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

    suspend fun insertMovieClip(movieClip: MovieClip) {
        movieClipRepository.insert(movieClip)
    }

    suspend fun removeMovieClip(movieClip: MovieClip) {
        movieClipRepository.delete(movieClip)
    }

}