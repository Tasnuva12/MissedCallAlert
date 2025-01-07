package com.example.missedcallalert.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.missedcallalert.AppConfigurationResponse
import com.example.missedcallalert.api.LogInAPI.LoginResponse
import com.example.missedcallalert.Resource
import com.example.missedcallalert.api.LogInAPI.LoginRepository
import com.example.missedcallalert.api.OTPResponseDataFormat
import com.example.missedcallalert.api.OtpRequestRepository
import com.example.missedcallalert.api.OtpVerifictionAPI.OtpVerificationRepository
import com.example.missedcallalert.data.Country
import com.example.missedcallalert.data.SessionPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

import kotlinx.coroutines.launch

import javax.inject.Inject
@HiltViewModel
class OtpViewModel @Inject constructor(
    private val otpRequestRepository: OtpRequestRepository,
    private val otpVerificationRepository: OtpVerificationRepository,
    sessionPreference: SessionPreference

): ViewModel() {

    val mPref = sessionPreference
    private val _otpResponse = MutableLiveData<Result<OTPResponseDataFormat>>()
    val otpResponse: LiveData<Result<OTPResponseDataFormat>> get() = _otpResponse
    private val _appConfigFlow= MutableStateFlow<Resource<AppConfigurationResponse>>(Resource.Loading)
    val appConfigFlow =_appConfigFlow.asStateFlow()

    private val _loginFlow =
        MutableStateFlow<Resource<LoginResponse?>>(Resource.Loading)
    val loginFlow = _loginFlow.asStateFlow()

    private val _otpVerificationResult = MutableLiveData<Result<Boolean>>()
    val otpVerificationResult: LiveData<Result<Boolean>> get() = _otpVerificationResult





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
    fun validateOtp(inputOtp: String, username: String) {
        viewModelScope.launch {
            try {
                // Pass the OTP and username to the repository for verification
                val result = otpVerificationRepository.otpVerificationFunction(inputOtp, username)
                 _otpVerificationResult.postValue(Result.success(true))

            } catch (e: Exception) {
                _otpVerificationResult.postValue(Result.success(false))
            }
        }
    }

    fun login() {
       viewModelScope.launch{
           try{
               val result=LoginRepository.
           }
       }
    }


}