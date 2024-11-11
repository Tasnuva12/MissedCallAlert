package com.example.missedcallalert.ui.Screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun HomeScreen(modifier: Modifier =Modifier){
    Box(modifier = modifier.fillMaxSize()){
       Box(modifier=Modifier.fillMaxWidth().height(58.dp)){
           Text("this is a box")
       }
    }
}

