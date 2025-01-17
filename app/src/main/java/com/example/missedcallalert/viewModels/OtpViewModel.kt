package com.example.missedcallalert.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.missedcallalert.AppConfigurationResponse
import com.example.missedcallalert.AppConfigurationService
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
    private val loginRepository: LoginRepository,
    private val  applicationConfigurationService: AppConfigurationService,
    sessionPreference: SessionPreference


): ViewModel() {

    val mPref = sessionPreference
    //registration (requestOTP)
    private val _otpResponse =  MutableStateFlow<Resource<OTPResponseDataFormat>>(Resource.Loading)
    val otpResponse = _otpResponse.asStateFlow()

   //app configuration
    private val _appConfigFlow= MutableStateFlow<Resource<AppConfigurationResponse>>(Resource.Loading)
    val appConfigFlow =_appConfigFlow.asStateFlow()

    //login(login)
    private val _loginFlow =
        MutableStateFlow<Resource<LoginResponse?>>(Resource.Loading)
    val loginFlow = _loginFlow.asStateFlow()

    //otp verification(validateOTP)
    private val _otpVerificationResult = MutableLiveData<Result<Boolean>>()
    val otpVerificationResult: LiveData<Result<Boolean>> get() = _otpVerificationResult




    //otp request
    fun requestOtp(phoneNumber: String, selectedCode: Country) {
        viewModelScope.launch {
            try {
                // Set loading state
                _otpResponse.value = Resource.Loading
                Log.d("OtpRequest", "OTP request started. Waiting for response...")

                // Attempt to fetch OTP
                val result = otpRequestRepository.otpRequestFunction(phoneNumber, selectedCode)

                // If successful, update the response
                if (result.isSuccess) {
                    val otpData = result.getOrNull()
                    _otpResponse.value = Resource.Success(otpData!!)
                    Log.d("OtpRequest", "OTP retrieved successfully: $otpData")
                } else {
                    // If the Result indicates failure, handle the error
                    throw result.exceptionOrNull() ?: Exception("Unknown error occurred")
                }
            } catch (e: Exception) {
                // Handle exceptions by updating the response state
                _otpResponse.value = Resource.Failure(e)
                Log.d("OtpRequest", "Failed to retrieve OTP: ${e.message}")
            }
        }
    }

    //otp  validation
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
    //otp login
    fun login() {
       viewModelScope.launch{
           try {
               _loginFlow.value=Resource.Loading
               val result = loginRepository.execute()
               _loginFlow.value=Resource.Success(result)

           }catch (error: Exception) {
               // Update state to failure with the error
               _loginFlow.value = Resource.Failure(error)
           }
       }
    }
    fun getAppConfig(){
        viewModelScope.launch{
            try{
                _appConfigFlow.value=Resource.Loading
                val result=applicationConfigurationService.execute()
                _appConfigFlow.value= Resource.Success(result)

            }
            catch(error: Exception){
                _appConfigFlow.value=Resource.Failure(error)
            }
        }
    }








}

