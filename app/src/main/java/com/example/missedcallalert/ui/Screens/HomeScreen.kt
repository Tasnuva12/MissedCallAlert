package com.example.missedcallalert.ui.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.missedcallalert.R
import com.example.missedcallalert.ui.Components.CustomText
import com.example.missedcallalert.ui.Components.bottomEdgeShadow

@Composable
fun HomeScreen(modifier: Modifier = Modifier, navController: NavHostController) {
    Column(modifier=Modifier,
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(0.dp)
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(58.dp)
                .background(Color.White, shape = RoundedCornerShape(12.dp))
                .bottomEdgeShadow(
                    color = Color(0x55000000), // Shadow color
                    shadowHeight = 1.dp,
                    cornerRadius = 12.dp
                )
                .padding(16.dp)
        ) {
            CustomText(
                text = "Miss Call Alert",
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = roboto,
                color = Color.Black,
                textAlign = TextAlign.Left,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Image(
            painter = painterResource(R.drawable.hello),
            contentDescription = "Hello",
            contentScale = ContentScale.Crop,
            modifier = Modifier.width(134.dp).padding(start=16.dp)

        )
        CustomText(
            text = "Mahfujul Alam",
            fontSize = 28.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = roboto,
            color = Color.Black,
            textAlign = TextAlign.Left,
            modifier = Modifier.fillMaxWidth().padding(start=16.dp,top=5.dp,bottom=20.dp)
        )
        CustomText(
            text = "Enable Missed Call Alert for",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = roboto,
            color = Color.Black,
            textAlign = TextAlign.Left,
            modifier = Modifier.fillMaxWidth().padding(start=16.dp,bottom=10.dp)
        )
        HorizontalDivider(thickness = 1.dp, modifier=Modifier.padding(start=16.dp,end=16.dp,bottom=10.dp))








    }
}
