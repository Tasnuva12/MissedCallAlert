package com.example.missedcallalert.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.missedcallalert.api.OTPResponseDataFormat
import com.example.missedcallalert.api.OtpApi
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

class OtpViewModel @Inject constructor(private val otpApi: OtpApi): ViewModel() {
    private  val  _otpResponse=MutableLiveData<Response<OTPResponseDataFormat>>()
    val otpResponse: LiveData<Response<OTPResponseDataFormat>> get() = _otpResponse
    fun requestOtp(phoneNumber:String){
        viewModelScope.launch{
            try{
                val response = otpApi.requestOtp(OtpRequest(phoneNumber))
            }
        }
    }



}