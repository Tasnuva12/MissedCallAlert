package com.example.missedcallalert.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.missedcallalert.AppConfigurationResponse
import com.example.missedcallalert.LoginResponse
import com.example.missedcallalert.Resource
import com.example.missedcallalert.data.Country
import com.example.missedcallalert.data.SessionPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    sessionPreference: SessionPreference

):ViewModel() {

    val mPref = sessionPreference

    private val _appConfigFlow= MutableStateFlow<Resource<AppConfigurationResponse>>(Resource.Loading)
    val appConfigFlow =_appConfigFlow.asStateFlow()

    private val _loginFlow =
        MutableStateFlow<Resource<LoginResponse?>>(Resource.Loading)
    val loginFlow = _loginFlow.asStateFlow()

    //phone number
    private val _phoneNumber=MutableLiveData<String>()
    val phoneNumber:LiveData<String> get()=_phoneNumber

    //country code
    private val _country=MutableLiveData<Country>()
    val country :LiveData<Country> get()= _country




    fun setPhoneNumber(number:String){
        _phoneNumber.value=number
    }
    fun setCountryCode(country: Country){
        _country.value=country
    }

    fun login() {
        viewModelScope.launch {
            val response = resultFromExternalResponse {
                loginApiService.execute()
            }
            _loginFlow.value = response
        }
    }

}