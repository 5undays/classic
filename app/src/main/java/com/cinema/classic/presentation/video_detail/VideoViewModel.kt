package com.cinema.classic.presentation.video_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.cinema.classic.common.Resource
import com.cinema.classic.data.local.dto.MovieClip
import com.cinema.classic.data.repository.MovieClipRepository
import com.cinema.classic.domain.use_case.get_kmdb.GetKmdbUserCase
import com.cinema.classic.domain.use_case.get_naver.GetNaverUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class VideoViewModel @Inject constructor(
    private val getNaverUseCase: GetNaverUseCase,
    private val getKmdbUserCase: GetKmdbUserCase,
//    private val movieClipRepository: MovieClipRepository,
    private val handle: SavedStateHandle
) : ViewModel() {
    val videoId = handle.get<String>("video_id")
    val year = handle.get<Int>("year")
    val title = handle.get<String>("title")

    init {
        if (title != null && year != null) {
            getMovieDetail(title, year)
            getPlot(title)
        }
    }

    private fun getMovieDetail(title: String, year: Int) {
        getNaverUseCase(title, year).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _data.value = NaverMovieState(isLoading = true)
                }
                is Resource.Success -> {
                    _data.value = NaverMovieState(movie = result.data)
                }
                is Resource.Error -> {
                    _data.value =
                        NaverMovieState(error = result.message ?: "An unexpected error occured")
                }
            }
        }
    }

    private fun getPlot(title: String) {
        getKmdbUserCase(title).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _plotDtoData.value = PlotState(isLoading = true)
                }
                is Resource.Success -> {
                    _plotDtoData.value = PlotState(movie = result.data)
                }
                is Resource.Error -> {
                    _plotDtoData.value =
                        PlotState(error = result.message ?: "An unexpected error occured")
                }
            }
        }
    }

    private val _data = mutableStateOf(NaverMovieState())
    val data: State<NaverMovieState> get() = _data
//    val movieClipList: LiveData<List<MovieClip>> =
//        movieClipRepository.get(videoId.toString()).asLiveData()
    private val _plotDtoData = mutableStateOf(PlotState())
    val plotDtoData: State<PlotState> = _plotDtoData

//    suspend fun insertMovieClip(movieClip: MovieClip) {
//        movieClipRepository.insert(movieClip)
//    }
//
//    suspend fun removeMovieClip(movieClip: MovieClip) {
//        movieClipRepository.delete(movieClip)
//    }

}