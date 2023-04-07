package com.cinema.classic.common

sealed class Resource<T>(val data: T? = null, val message: String? = "") {
    class Success
    class Error
    class Loading()
}
