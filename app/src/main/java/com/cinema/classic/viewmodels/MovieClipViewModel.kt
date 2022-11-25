package com.cinema.classic.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.cinema.classic.repository.MovieClipRepository
import javax.inject.Inject

class MovieClipViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val movieClipRepository: MovieClipRepository
) : ViewModel() {

}