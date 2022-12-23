package com.cinema.classic.viewmodels

import androidx.lifecycle.*
import com.cinema.classic.data.MovieClip
import com.cinema.classic.model.Item
import com.cinema.classic.model.NaverMovie
import com.cinema.classic.data.MovieClipRepository
import com.cinema.classic.data.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MovieRepository,
    private val movieClipRepository: MovieClipRepository
) : ViewModel() {
    private val _data: MutableLiveData<List<Item>> = MutableLiveData<List<Item>>();
    val uploadList: LiveData<List<Item>> get() = _data
    val lastClip: LiveData<MovieClip> get() = movieClipRepository.getLastVideo().asLiveData()

    init {
        viewModelScope.launch {
            val response = repository.load();
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