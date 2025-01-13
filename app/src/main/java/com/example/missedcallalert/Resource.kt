package com.example.missedcallalert

import com.google.gson.annotations.SerializedName

sealed class Resource <out T>{
    data class Success<out T>(
        val data:T
    ):Resource<T>()

    data class Failure <out T>(
        @SerializedName("error")
        val error: Exception
    ):Resource<T>()

    data object Loading : Resource<Nothing>()


}