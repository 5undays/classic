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
import kotlinx.coroutines.flow.*
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

    private val _data = mutableStateOf(
        MovieState(
            movie = Kmdb(
                title = title,
                thumbnail = "",
                director = "",
                year = year,
                actors = "",
                plot = ""
            )
        )
    )
    val data: State<MovieState> get() = _data

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val kmdb: Flow<Resource<Kmdb>> = videoViewUseCase.getKmdbUserCase(title)
    private val movieClips: Flow<List<MovieClip>> = videoViewUseCase.getMovieClips(videoId)

    init {
        collectFlow()
    }

    private fun collectFlow() {
        combine(kmdb, movieClips) { kmdb, movieClips ->
            _data.value = data.value.copy(movieClips = movieClips)

            when (kmdb) {
                is Resource.Success -> {
                    if (kmdb.data != null) {
                        _data.value = data.value.copy(movie = kmdb.data)
                    }
                }
                is Resource.Error -> {
                    _data.value =
                        data.value.copy(
                            error = kmdb.message ?: "An unexpected error occured",
                            isLoading = false
                        )
                }
            }
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
                ///activity?.requestedOrientation = event.orientation
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