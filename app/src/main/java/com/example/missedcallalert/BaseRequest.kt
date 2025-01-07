package com.example.missedcallalert

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


open class BaseRequest(


//    @SerialName("username") val username: String,
//    @SerialName("deviceType") val deviceType: Int,
//    @SerialName("deviceUniqueId") val deviceUniqueId: String,
//    @SerialName("apiName") val apiName: String,
//    @SerialName("appVersionCode") val appVersionCode: Int
    @SerializedName("username")
    var username:String?=null,

    @SerializedName("deviceType")
    var deviceType:Int=0,
    @SerializedName("apiName")
    var apiName:String?=null,
    @SerializedName("appVersionCode")
    var appVersionCode:Int=0



)
