package com.cinema.classic.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cinema.classic.model.Item
import com.cinema.classic.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {
    private val _data: MutableLiveData<List<Item>> = MutableLiveData<List<Item>>();
    val data: LiveData<List<Item>> get() = _data

    init {
        viewModelScope.launch {
            val response = repository.load();
            if (response.isSuccessful) {
                _data.value = response.body()?.items
            }
        }
    }

}