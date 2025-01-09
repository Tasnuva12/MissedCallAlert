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
    @SerialName("username")
    var username:String?=null,

    @SerialName("deviceType")
    var deviceType:Int=0,
    @SerialName("apiName")
    var apiName:String?=null,
    @SerialName("appVersionCode")
    var appVersionCode:Int=0,
    @SerialName("deviceUniqueId")
    var deviceUniqueId:String?=null



) {

}
