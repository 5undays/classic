package com.cinema.classic.presentation.video_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cinema.classic.common.Resource
import com.cinema.classic.common.UiEvent
import com.cinema.classic.domain.model.InvalidMovieClipException
import com.cinema.classic.domain.model.Kmdb
import com.cinema.classic.domain.model.MovieClip
import com.cinema.classic.domain.use_case.VideoViewUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class VideoViewModel @Inject constructor(
    private val videoViewUseCase: VideoViewUseCase,
    private val handle: SavedStateHandle
) : ViewModel() {
    val videoId: String = checkNotNull(handle["videoId"])
    val year: Int = checkNotNull(handle["year"])
    val title: String = checkNotNull(handle["title"])

    private val _data = mutableStateOf(MovieState())
    val data: State<MovieState> get() = _data

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        getMovieDetail(title)
        getMovieClips(videoId)
    }

    private fun getMovieDetail(title: String) {
        videoViewUseCase.getKmdbUserCase(title).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _data.value = MovieState(isLoading = true)
                }
                is Resource.Success -> {
                    if (result.data == null) {
                        _data.value = data.value.copy(
                            movie = Kmdb(
                                title = title,
                                thumbnail = "",
                                director = "",
                                year = year,
                                actors = "",
                                plot = ""
                            )
                        )
                    } else {
                        _data.value = data.value.copy(movie = result.data, isLoading = false)
                    }
                }
                is Resource.Error -> {
                    _data.value =
                        data.value.copy(
                            error = result.message ?: "An unexpected error occured",
                            isLoading = false
                        )
                }
            }
        }.launchIn(viewModelScope)
    }

    private var getMovieClipJob: Job? = null
    private fun getMovieClips(videoId: String) {
        getMovieClipJob?.cancel()
        getMovieClipJob = videoViewUseCase.getMovieClips(videoId).onEach { result ->
            _data.value = data.value.copy(movieClips = result)
        }.launchIn(viewModelScope)

    }

    fun onEvent(event: VideoViewEvent) {
        when (event) {
            is VideoViewEvent.AddMovieClip -> {
                GlobalScope.launch {
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
                    videoViewUseCase.deleteMovieClip(event.movieClip)
                }
            }
            is VideoViewEvent.SetOrientation -> {
                //activity?.requestedOrientation = event.orientation
            }
            is VideoViewEvent.GetCurrentTime -> {
                _data.value.currentTime = event.currentTime
            }
        }
    }

    override fun onCleared() {
        _data.value.let {
            onEvent(
                VideoViewEvent.AddMovieClip(
                    MovieClip(
                        video_id = videoId,
                        movie_name = _data.value.movie!!.title,
                        movie_year = _data.value.movie!!.year,
                        reg_date = Calendar.getInstance(),
                        time = _data.value.currentTime
                    )
                )
            )
        }
        super.onCleared()
    }

}