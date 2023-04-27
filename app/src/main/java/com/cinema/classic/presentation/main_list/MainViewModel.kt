package com.cinema.classic.presentation.main_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.cinema.classic.common.Resource
import com.cinema.classic.domain.model.MovieClip
import com.cinema.classic.domain.use_case.MainUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
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


    private val _data = mutableStateOf(MainViewState())
    val data: State<MainViewState> = _data

    private val _lastClip: Flow<MovieClip> = mainUseCase.getLastMovieClip()

    val lastClip: StateFlow<MovieClip?> = _lastClip.stateIn(
        scope = viewModelScope,
        started = WhileSubscribed(5000),
        initialValue = _data.value.lastClip
    )

}