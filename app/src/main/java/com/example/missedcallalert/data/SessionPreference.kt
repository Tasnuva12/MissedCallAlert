package com.example.missedcallalert.data

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class SessionPreference(private val pref: SharedPreferences, context: Context) {

    val fcmToken: String="abed1234eff5678"

    // Define LiveData properties
    private val _userId = MutableLiveData<Int>()
    val userId: LiveData<Int> get() = _userId

    private val _username = MutableLiveData<String?>()
    val username: LiveData<String?> get() = _username

    private val _password = MutableLiveData<String?>()
    val password: LiveData<String?> get() = _password

    private val _isVerified = MutableLiveData<Int>()
    val isVerified: LiveData<Int> get() = _isVerified

    private val _isPremium = MutableLiveData<Int>()
    val isPremium: LiveData<Int> get() = _isPremium



    companion object {
        private const val PREF_NAME = "SessionPreference"
        private const val KEY_USER_ID = "userId"
        private const val KEY_USERNAME = "username"
        private const val KEY_PASSWORD = "password"
        private const val KEY_IS_VERIFIED = "isVerified"
        private const val KEY_IS_PREMIUM = "isPremium"
        private const val PREF_DEVICE_TYPE = "pref_device_type"
        private const val PREF_USER_IP="pref_user_ip"
        private const val PREF_PHONE_NUMBER = "pref_number"
    }

    // Method to save session data
    fun saveSession(userId: Int, username: String, password: String, isVerified: Int, isPremium: Int) {
        pref.edit().apply {
            putInt(KEY_USER_ID, userId)
            putString(KEY_USERNAME, username)
            putString(KEY_PASSWORD, password)
            putInt(KEY_IS_VERIFIED, isVerified)
            putInt(KEY_IS_PREMIUM, isPremium)
            apply()
        }

        // Update LiveData values
        _userId.value = userId
        _username.value = username
        _password.value = password
        _isVerified.value = isVerified
        _isPremium.value = isPremium
    }

    // Method to load session data into LiveData
    fun loadSession() {
        _userId.value = pref.getInt(KEY_USER_ID, -1)
        _username.value = pref.getString(KEY_USERNAME, null)
        _password.value = pref.getString(KEY_PASSWORD, null)
        _isVerified.value = pref.getInt(KEY_IS_VERIFIED, 0)
        _isPremium.value = pref.getInt(KEY_IS_PREMIUM, 0)
    }

    // Method to clear session data
    fun clearSession() {
        pref.edit().clear().apply()

        // Reset LiveData
        _userId.value = -1
        _username.value = null
        _password.value = null
        _isVerified.value = 0
        _isPremium.value = 0
    }

    var deviceType: Int
        get() = pref.getInt(PREF_DEVICE_TYPE, 1)
        set(value) = pref.edit { putInt(PREF_DEVICE_TYPE, value) }
    var userIp: String
        get() = pref.getString(PREF_USER_IP, "") ?: ""
        set(value) = pref.edit { putString(PREF_USER_IP, value) }

    var phoneNumber: String
        get() = pref.getString(PREF_PHONE_NUMBER, "") ?: ""
        set(phoneNumber) = pref.edit { putString(PREF_PHONE_NUMBER, phoneNumber) }
}
