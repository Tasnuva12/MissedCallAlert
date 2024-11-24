package com.example.missedcallalert.ui.Components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import org.w3c.dom.Text

@Composable
fun CustomText(
    text: String="This is a text",
    fontSize:TextUnit=14.sp,
    fontWeight: FontWeight=FontWeight.Normal,
    modifier: Modifier,
    fontFamily:FontFamily,
    color: Color = MaterialTheme.colorScheme.tertiary,

    textAlign: TextAlign = TextAlign.Start,
){
    Text(
        text = text,
        modifier = modifier,
        color=color,
        textAlign = textAlign,
        fontSize = fontSize,
        fontWeight = fontWeight
    )


}