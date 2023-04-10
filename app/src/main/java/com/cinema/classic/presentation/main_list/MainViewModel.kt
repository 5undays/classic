package com.cinema.classic.presentation.main_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.cinema.classic.common.Resource
import com.cinema.classic.domain.model.MovieClip
import com.cinema.classic.data.repository.MovieClipRepositoryImpl
import com.cinema.classic.domain.use_case.MainUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainUseCase: MainUseCase
) : ViewModel() {
    private val _data = mutableStateOf(MainViewState())
    val data: State<MainViewState> = _data
    init {
        getYoutubeList()
    }

    private fun getYoutubeList() {
        mainUseCase.getYoutubeListUseCase().onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _data.value =
                        MainViewState(error = result.message ?: "An unexpected error occured")
                }
                is Resource.Success -> {
                    _data.value = MainViewState(youtubeList = result.data ?: emptyList())
                }
                is Resource.Loading -> {
                    _data.value = MainViewState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getMovieDetail(title: String, year: Int) {
        mainUseCase.getNaverUseCase(title, year).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _data.value = MainViewState(isLoading = true)
                }
                is Resource.Success -> {
                    _data.value = MainViewState(movie = result.data)
                }
                is Resource.Error -> {
                    _data.value =
                        MainViewState(error = result.message ?: "An unexpected error occured")
                }
            }
        }
    }
}