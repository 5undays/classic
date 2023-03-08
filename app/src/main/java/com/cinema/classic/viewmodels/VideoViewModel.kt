package com.cinema.classic.viewmodels

import androidx.lifecycle.*
import com.cinema.classic.data.MovieClip
import com.cinema.classic.data.NaverMovie
import com.cinema.classic.data.Plot
import com.cinema.classic.data.MovieClipRepository
import com.cinema.classic.data.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideoViewModel @Inject constructor(
    private val repository: MovieRepository,
    private val movieClipRepository: MovieClipRepository,
    private val handle: SavedStateHandle
) : ViewModel() {
    val videoId = handle.get<String>("video_id")
    val year = handle.get<Int>("year")
    val title = handle.get<String>("title")

    init {
        viewModelScope.launch {
            val response = repository.get(title!!, year!!)
            if (response.isSuccessful) {
                _data.value = response.body()?.items?.get(0)
            }
        }

        viewModelScope.launch {
            val response = repository.get2(title!!)
            if (response.isSuccessful) {
                _plotData.value = response.body()?.Data?.get(0)?.Result?.get(0)?.plots?.plot?.get(0)
            }
        }
    }

    private val _data: MutableLiveData<NaverMovie> = MutableLiveData()
    val data: LiveData<NaverMovie> get() = _data
    val movieClipList: LiveData<List<MovieClip>> =
        movieClipRepository.get(videoId.toString()).asLiveData()
    private val _plotData: MutableLiveData<Plot> = MutableLiveData()
    val plotData: LiveData<Plot> get() = _plotData

    suspend fun insertMovieClip(movieClip: MovieClip) {
        movieClipRepository.insert(movieClip)
    }

    suspend fun removeMovieClip(movieClip: MovieClip) {
        movieClipRepository.delete(movieClip)
    }

}