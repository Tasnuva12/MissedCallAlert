package com.example.missedcallalert

import com.example.missedcallalert.api.Api
import javax.inject.Inject

class AppConfigurationService  @Inject constructor(private val appconfig: Api){
    suspend fun execute(): AppConfigurationResponse{
        val response =appconfig.getApplicationConfigurations()
        return response
    }

}

