package com.cinema.classic.presentation.main_list

import androidx.lifecycle.*
import com.cinema.classic.data.local.dto.MovieClip
import com.cinema.classic.data.Item
import com.cinema.classic.data.NaverMovie
import com.cinema.classic.data.repository.MovieClipRepository
import com.cinema.classic.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MovieRepository,
    private val movieClipRepository: MovieClipRepository
) : ViewModel() {
    private val _data: MutableLiveData<List<Item>> = MutableLiveData<List<Item>>()
    val uploadList: LiveData<List<Item>> get() = _data
    val lastClip: LiveData<MovieClip> get() = movieClipRepository.getLastVideo().asLiveData()

    init {
        viewModelScope.launch {
            val response = repository.load()
            if (response.isSuccessful) {
                _data.value = response.body()?.items
            }
        }
    }

    private val _data2: MutableLiveData<NaverMovie> = MutableLiveData()
    val data2: LiveData<NaverMovie> get() = _data2
    fun getMovieDetail(title: String, year: Int) = viewModelScope.launch {
        val response = repository.get(title, year)
        if (response.isSuccessful) {
            _data2.value = response.body()?.items?.get(0)
        }
    }
}