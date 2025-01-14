package com.example.missedcallalert.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.missedcallalert.data.Country
import com.example.missedcallalert.data.SessionPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    sessionPreference: SessionPreference

):ViewModel() {





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
    fun getAppConfig() {
        viewModelScope.launch {
            val response = resultFromExternalResponse {
                applicationConfigurationService.execute()
            }
            _appConfigFlow.value = response
        }
    }




}