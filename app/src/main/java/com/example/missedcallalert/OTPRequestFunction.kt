package com.example.missedcallalert

import android.util.Log
import android.widget.Toast
import com.example.missedcallalert.data.Country
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun otpRequestFunction(phoneNo: String, selectedCode: Country){
    val request=OtpRequest(
        username = "${selectedCode.code}$phoneNo",
        deviceType = 1,
        deviceUniqueId = "sifat404040@gmail.com",
        apiName = "registration",

        appVersionCode =1,
    )

    val apiService=APIServiceGenerator.apiService
    val call =apiService.verifyOtp(request)

    call.enqueue(object : Callback<OTPResponseDataFormat> {
        override fun onResponse(
            call: Call<OTPResponseDataFormat>,
            response: Response<OTPResponseDataFormat>
        ) {
            Log.d("RawResponse", response.toString())
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null) {
                    println("OTP Verification Successful: ${responseBody.status}")
                    Log.d("Otp","Successful")

                } else {

                    println("Response body is null")
                    Log.d("Otp","Not Successful")



                }
            } else {

                println("Error: ${response.code()} - ${response.message()}")
            }
        }

        override fun onFailure(call: Call<OTPResponseDataFormat>, t: Throwable) {

            println("Network Error: ${t.message}")
        }
    })

}