package com.example.missedcallalert.ui.Screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.missedcallalert.R


@Composable
fun HomeScreen(modifier: Modifier =Modifier){
    val shadowColor = colorResource( R.color.grey)

       Column(modifier=modifier.fillMaxWidth().height(58.dp)){
           Text("this is a box")
           Spacer(modifier = Modifier.height(20.dp))
           HorizontalDivider(
               Modifier.shadow(
                   elevation = 2.dp,
                   shape = RoundedCornerShape(0.dp),
                   spotColor =shadowColor
               ).fillMaxWidth()
           )
       }

}

