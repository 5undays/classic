package com.cinema.classic.viewmodels

import androidx.lifecycle.*
import com.cinema.classic.data.interest.MovieClip
import com.cinema.classic.model.Item
import com.cinema.classic.repository.MovieClipRepository
import com.cinema.classic.repository.MovieRepository
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

}