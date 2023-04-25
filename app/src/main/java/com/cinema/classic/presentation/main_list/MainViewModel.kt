package com.cinema.classic.presentation.main_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.cinema.classic.domain.use_case.MainUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainUseCase: MainUseCase
) : ViewModel() {

    val mainVideos = Pager(
        PagingConfig(pageSize = 10)
    ) {
        YoutubePagingSource(mainUseCase.getYoutubeListUseCase)
    }.flow.cachedIn(viewModelScope)

    private val _lastVideo = MutableStateFlow(mainUseCase.getLastMovieClip)
    val lastVideo = _lastVideo

//    private val _data = mutableStateOf(MainViewState())
//    val data: State<MainViewState> = _data


//    fun getMovieDetail(title: String, year: Int) {
//        mainUseCase.getNaverUseCase(title, year).onEach { result ->
//            when (result) {
//                is Resource.Loading -> {
//                    _data.value = MainViewState(isLoading = true)
//                }
//                is Resource.Success -> {
//                    _data.value = MainViewState(movie = result.data)
//                }
//                is Resource.Error -> {
//                    _data.value =
//                        MainViewState(error = result.message ?: "An unexpected error occured")
//                }
//            }
//        }
//    }
}