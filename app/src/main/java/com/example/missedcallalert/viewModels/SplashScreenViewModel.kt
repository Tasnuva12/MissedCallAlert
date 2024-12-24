package com.example.missedcallalert.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.missedcallalert.data.Country
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


class SplashScreenViewModel(

):ViewModel() {




    //phone number
    private val _phoneNumber=MutableLiveData<String>()
    val phoneNumber:LiveData<String> get()=_phoneNumber

    //country code
    private val _country=MutableLiveData<Country>()
    val country :LiveData<Country> get()= _country

    //for timer value
    private val _timer =MutableLiveData<Int>()
    val timer:LiveData<Int> get()= _timer

    private var countdownJob: Job? = null

    // Starts the countdown timer
    fun startTimer(duration: Int = 55) {
        countdownJob?.cancel() // Cancel any existing timer
        _timer.value = duration
        countdownJob = viewModelScope.launch {
            for (time in duration downTo 0) {
                _timer.postValue(time)
                delay(1000L) // Wait 1 second

            }
        }
    }

    // Stops the countdown timer
    fun stopTimer() {
        countdownJob?.cancel()
        _timer.value = 0
    }






    fun setPhoneNumber(number:String){
        _phoneNumber.value=number
    }
    fun setCountryCode(country: Country){
        _country.value=country
    }




}