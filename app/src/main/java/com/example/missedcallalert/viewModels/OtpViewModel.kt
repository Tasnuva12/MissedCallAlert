package com.example.missedcallalert.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.missedcallalert.api.OTPResponseDataFormat
import com.example.missedcallalert.api.OtpRequestRepository
import com.example.missedcallalert.data.Country
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.launch

import javax.inject.Inject
@HiltViewModel
class OtpViewModel @Inject constructor(
    private val otpRequestRepository: OtpRequestRepository
): ViewModel() {
    private val _otpResponse = MutableLiveData<Result<OTPResponseDataFormat>>()
    val otpResponse: LiveData<Result<OTPResponseDataFormat>> get() = _otpResponse
    fun requestOtp(phoneNumber: String, selectedCode: Country){
        viewModelScope.launch{
            try{
               val result= otpRequestRepository.otpRequestFunction(phoneNumber,selectedCode)
                _otpResponse.postValue(result)

            }catch (e:Exception){
                _otpResponse.postValue(Result.failure(e))
            }
        }
    }



}