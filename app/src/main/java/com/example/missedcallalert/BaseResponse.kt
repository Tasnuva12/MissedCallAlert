package com.example.missedcallalert

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

open class BaseResponse {
    @SerializedName("status")
    var status:Int=0

    @SerializedName("apiName")
    var apiName:String?=null

    @SerializedName("errorCode")
    var errorCode:Int=0

    @SerializedName("errorMessage")
    var errorMessage:String?=null

}