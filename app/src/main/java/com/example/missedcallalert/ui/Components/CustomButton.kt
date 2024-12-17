package com.example.missedcallalert.ui.Components

import android.Manifest
import android.content.Context
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.missedcallalert.data.Country
import com.example.missedcallalert.ui.theme.appColor
import com.example.missedcallalert.viewModels.OtpViewModel
import com.example.missedcallalert.viewModels.SplashScreenViewModel

@Composable
fun CustomButton(
    modifier: Modifier,

    text:String,
    onClick: () -> Unit




){

    Box(modifier= modifier
        .width(250.dp)
        .clip(RoundedCornerShape(50.dp))
        .background(color = Color.White)
        .padding(10.dp)
        .clickable {onClick()},
        contentAlignment = Alignment.Center


    ){
        Text(text, color= appColor, style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp))
    }


}

