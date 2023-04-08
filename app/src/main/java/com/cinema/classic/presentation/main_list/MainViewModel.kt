package com.cinema.classic.presentation.main_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.cinema.classic.common.Resource
import com.cinema.classic.data.local.dto.MovieClip
import com.cinema.classic.data.repository.MovieClipRepository
import com.cinema.classic.domain.use_case.get_naver.GetNaverUseCase
import com.cinema.classic.domain.use_case.get_youtube.GetYoutubeListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getYoutubeListUseCase: GetYoutubeListUseCase,
    private val getNaverUseCase: GetNaverUseCase,
    private val movieClipRepository: MovieClipRepository
) : ViewModel() {
    private val _list = mutableStateOf(MainViewState())
    val list: State<MainViewState> = _list

    val lastClip: LiveData<MovieClip> get() = movieClipRepository.getLastVideo().asLiveData()
    private val _lastMovieData = mutableStateOf(LastMovieState())
    val lastMovieData: State<LastMovieState> = _lastMovieData

    init {
        getYoutubeList()
    }

    private fun getYoutubeList() {
        getYoutubeListUseCase().onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _list.value =
                        MainViewState(error = result.message ?: "An unexpected error occured")
                }
                is Resource.Success -> {
                    _list.value = MainViewState(youtubeList = result.data ?: emptyList())
                }
                is Resource.Loading -> {
                    _list.value = MainViewState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getMovieDetail(title: String, year: Int) {
        getNaverUseCase(title, year).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _lastMovieData.value = LastMovieState(isLoading = true)
                }
                is Resource.Success -> {
                    _lastMovieData.value = LastMovieState(movie = result.data)
                }
                is Resource.Error -> {
                    _lastMovieData.value =
                        LastMovieState(error = result.message ?: "An unexpected error occured")
                }
            }
        }
    }
}