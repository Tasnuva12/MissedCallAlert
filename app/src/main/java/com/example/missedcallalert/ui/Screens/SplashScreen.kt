package com.example.missedcallalert.ui.Screens


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import  androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.missedcallalert.R
import com.example.missedcallalert.ui.theme.appColor

val roboto = FontFamily(
    Font(R.font.archivo_condensed_semibold),
    Font(R.font.archivo_condensed_bold),
    Font(R.font.archivo_condensed_regular)
)

data class Country(
    val code:String ,
    val flagRes:Int,
    val flagName:String
)
val countries= listOf(
    Country("+880",R.drawable.bangladesh,"Bangladesh") ,
    Country("+91",R.drawable.ic_indian_flag,"India")
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SplashScreen( modifier: Modifier = Modifier) {


    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(R.drawable.background),
            contentDescription = "background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()

        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center


        ) {
            Spacer(modifier = Modifier.height(60.dp))

            Image(
                painterResource(R.drawable.vector),
                contentDescription = "missedcall",
                alignment = Alignment.Center,
                modifier = Modifier
                    .height(106.15.dp)
                    .width(136.15.dp),

                )
            Spacer(modifier = Modifier
                .padding(top = 16.dp)
                .height(8.dp)
                .fillMaxWidth())
            Text(
                text = "MISSED CALL",
                color = Color.White,
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
                fontWeight = FontWeight.W700,
                modifier = Modifier.fillMaxWidth(),
            )
            Text(
                text = "ALERT",
                color = Color.White,
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
                fontWeight = FontWeight.W700,
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(80.dp))
            Text(
                text = "Welcome to",
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.SemiBold,
                fontFamily = roboto,
                fontSize = 18.sp
            )
            Text(
                text = "Missed Call Alert App",
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                fontFamily = roboto,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp
            )

            Text(
                text = "Enter your number to verify",
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                fontFamily = roboto,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(20.dp))
            CountryPhoneInput()

        }




    }}

@Composable
fun CountryPhoneInput(){
    var expanded by remember { mutableStateOf(false) }

    var selectedCode by remember { mutableStateOf(countries.first()) }
    var phoneNo by remember { mutableStateOf("") }
    val showDialog = remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp).height(70.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
           Card( modifier = Modifier.width(120.dp).height(40.dp),
               shape = RoundedCornerShape(8.dp),
               backgroundColor = appColor,
               border = BorderStroke(1.dp, Color.White)){
               Row(
                   modifier = Modifier.fillMaxWidth().padding(8.dp).clickable { expanded = true },
                   verticalAlignment = Alignment.CenterVertically
               ){
                   Image(
                       painter = painterResource(id = R.drawable.polygon2),
                       contentDescription = "Dropdown Icon",
                       modifier = Modifier.size(12.dp).clip(RoundedCornerShape(4.dp)),
                       contentScale = ContentScale.Crop
                   )
                   Spacer(modifier = Modifier.width(8.dp))
                   Image(
                       painter = painterResource(id = selectedCode.flagRes),
                       contentDescription = "Country Flag",
                       modifier = Modifier.size(24.dp)
                   )
                   Spacer(modifier = Modifier.width(8.dp))
                   Text(
                       text = selectedCode.code,
                       fontSize = 14.sp,
                       color = Color.White
                   )
               }

           }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ){
                val scrollState = rememberScrollState()

                Column(
                    modifier = Modifier
                        .heightIn(max = 200.dp)
                        .verticalScroll(scrollState)
                ) {
                    countries.forEach { item ->
                        androidx.compose.material.DropdownMenuItem(
                            onClick = {
                                selectedCode = item
                                expanded = false
                            }
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(id = item.flagRes),
                                    contentDescription = null,
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                androidx.compose.material.Text(text = item.code)
                            }
                        }
                    }
                }
                }
            Spacer(modifier = Modifier.width(16.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                backgroundColor = appColor,
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, Color.White)
            ) {
                val interactionSource = remember { MutableInteractionSource() }
                BasicTextField(
                    value = phoneNo,
                    onValueChange = { phoneNo = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp),
                    interactionSource = interactionSource,
                    singleLine = true,
                    textStyle = TextStyle(color = Color.White),
                    decorationBox = { innerTextField ->

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 8.dp)
                                .height(40.dp),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            innerTextField()
                        }
                    }
                )
            }

            }


        }

    }






