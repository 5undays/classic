package com.cinema.classic.presentation.video_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cinema.classic.common.Resource
import com.cinema.classic.common.UiEvent
import com.cinema.classic.domain.model.InvalidMovieClipException
import com.cinema.classic.domain.model.MovieClip
import com.cinema.classic.domain.use_case.VideoViewUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideoViewModel @Inject constructor(
    private val videoViewUseCase: VideoViewUseCase,
    private val handle: SavedStateHandle
) : ViewModel() {
    val videoId = handle.get<String>("video_id")
    val year = handle.get<Int>("year")
    val title = handle.get<String>("title")

    private val _data = mutableStateOf(MovieState())
    val data: State<MovieState> get() = _data

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()
    init {
        if (title != null && year != null) {
            getMovieDetail(title, year)
            getPlot(title)
        }
    }

    private fun getMovieDetail(title: String, year: Int) {
        videoViewUseCase.getNaverUseCase(title, year).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _data.value = MovieState(isLoading = true)
                }
                is Resource.Success -> {
                    _data.value = MovieState(movie = result.data)
                }
                is Resource.Error -> {
                    _data.value =
                        MovieState(error = result.message ?: "An unexpected error occured")
                }
            }
        }
    }

    private fun getPlot(title: String) {
        videoViewUseCase.getKmdbUserCase(title).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _data.value = MovieState(isLoading = true)
                }
                is Resource.Success -> {
                    _data.value = MovieState(plot = result.data)
                }
                is Resource.Error -> {
                    _data.value =
                        MovieState(error = result.message ?: "An unexpected error occured")
                }
            }
        }
    }

    private var getMovieClipJob: Job? = null
    private suspend fun getMovieClip(videoId: String) {
        getMovieClipJob?.cancel()
        getMovieClipJob = videoViewUseCase.getMovieClips(videoId).onEach {
            result -> _data.value = MovieState(movieClips = result)
        }.launchIn(viewModelScope)

    }
    fun onEvent(event: VideoViewEvent) {
        when (event) {
            is VideoViewEvent.AddMovieClip -> {
                viewModelScope.launch {
                    try {
                        videoViewUseCase.addMovieClip(event.movieClip)
                        _eventFlow.emit(UiEvent.save)
                    } catch (e: InvalidMovieClipException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Couldn't save note"
                            )
                        )
                    }
                }
            }
            is VideoViewEvent.DeleteMovieClip -> {
                viewModelScope.launch {
                    try {
                        videoViewUseCase.deleteMovieClip(event.movieClip)
                        _eventFlow.emit(UiEvent.save)
                    } catch (e: InvalidMovieClipException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Couldn't save note"
                            )
                        )
                    }
                }
            }
        }
    }
}