package com.muhammadhusniabdillah.themoviedb.data.network

import java.lang.Exception

sealed class Response<out R> {
    data class Success<out T>(val data: T) : Response<T>()
    data class Error(val exception: Exception) : Response<Nothing>()
}
