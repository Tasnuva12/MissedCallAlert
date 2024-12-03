package com.example.missedcallalert.ui.Screens


import android.graphics.Paint
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.missedcallalert.R
import com.example.missedcallalert.data.Country
import com.example.missedcallalert.ui.Components.CustomText
import com.example.missedcallalert.ui.theme.appColor
import com.example.missedcallalert.viewModels.OtpViewModel
import com.example.missedcallalert.viewModels.SplashScreenViewModel


val roboto = FontFamily(
    Font(R.font.archivo_condensed_semibold),
    Font(R.font.archivo_condensed_bold),
    Font(R.font.archivo_condensed_regular)
)

val countries= listOf(
    Country("+880",R.drawable.bangladesh,"Bangladesh") ,
    Country("+91",R.drawable.ic_indian_flag,"India")
)


@OptIn(ExperimentalMaterial3Api::class)
//UI of splashscreen

@Composable
fun SplashScreen( modifier: Modifier = Modifier) {
    val splashScreenViewModel: SplashScreenViewModel = viewModel()

    Box(modifier = modifier.fillMaxSize().verticalScroll(rememberScrollState()), contentAlignment = Alignment.Center) {
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
            CustomText(text = "MISSED CALL",
                color = Color.White,
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
                fontWeight = FontWeight.W700,
                modifier = Modifier.fillMaxWidth(),
                fontFamily = roboto,
            )
            CustomText(
                text = "ALERT",
                color = Color.White,
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
                fontWeight = FontWeight.W700,
                modifier = Modifier.fillMaxWidth(),
                fontFamily = roboto,
            )
            Spacer(modifier = Modifier.height(80.dp))
            CustomText(
                text = "Welcome to",
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.SemiBold,
                fontFamily = roboto,
                fontSize = 18.sp
            )
            CustomText(
                text = "Missed Call Alert App",
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                fontFamily = roboto,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp
            )

            CustomText(
                text = "Enter your number to verify",
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                fontFamily = roboto,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(20.dp))
            CountryPhoneInput(
                viewModel = splashScreenViewModel
            )

        }




    }}
//function for the Dropdown menu and Phone NO field
@Composable
fun CountryPhoneInput(viewModel: SplashScreenViewModel){
    val viewModelOtp: OtpViewModel = hiltViewModel()
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }
    //variables
    var expanded by remember { mutableStateOf(false) }
    val selectedCode by viewModel.country.observeAsState(countries.first())
    val phoneNo by viewModel.phoneNumber.observeAsState("")
    val permissionGranted = remember { mutableStateOf(false) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            permissionGranted.value = isGranted
        }
    )





    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(70.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
           Card( modifier = Modifier.background(appColor)
               .width(120.dp)
               .height(40.dp),
               shape = RoundedCornerShape(8.dp),

               border = BorderStroke(1.dp, Color.White)){
               Row(
                   modifier = Modifier
                       .fillMaxWidth()
                       .padding(8.dp)
                       .clickable { expanded = true },
                   verticalAlignment = Alignment.CenterVertically
               ){
                   Image(
                       painter = painterResource(id = R.drawable.polygon2),
                       contentDescription = "Dropdown Icon",
                       modifier = Modifier
                           .size(12.dp)
                           .clip(RoundedCornerShape(4.dp)),
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
                        DropdownMenuItem(
                            onClick = {
                                viewModel. setCountryCode(item)
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
                modifier = Modifier.background(appColor)
                    .fillMaxWidth()
                    .height(40.dp),

                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, Color.White)
            ) {
                val interactionSource = remember { MutableInteractionSource() }
                BasicTextField(
                    value = phoneNo,
                    onValueChange = {newPhoneNo->  viewModel.setPhoneNumber(newPhoneNo) },
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

            //Code for the button
              Box(modifier= Modifier
                  .width(250.dp)
                  .clip(RoundedCornerShape(50.dp))
                  .background(color = Color.White)
                  .padding(10.dp)
                  .clickable {
                      viewModelOtp.requestOtp(phoneNo,selectedCode)
                      showDialog = true

                  },
                      contentAlignment = Alignment.Center


              ){
                  Text("Generate OTP", color= appColor, style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp))
              }

        }
    if (showDialog){
        Dialog(
            onDismissRequest = {showDialog=false}) {
            Card (modifier = Modifier.fillMaxWidth().height(300.dp).padding(16.dp),shape = RoundedCornerShape(8.dp),

            ){


                    Column(modifier=Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally

                        ) {
                        Spacer(modifier = Modifier.height(16.dp))
                        Image(
                            painter= painterResource(R.drawable.contactbook),
                            contentDescription = "Icon of contact Book"
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = buildAnnotatedString {
                                append("Allow ")
                                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                    append("Missed Call Alert")
                                }
                                append(" to access your contacts?")
                            },
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.body1 // Optional, adjust the text style as needed
                        )
                        Spacer(modifier = Modifier.height(24.5.dp))
                        Divider(
                            color = appColor,
                            thickness = 1.dp
                        )
                        Spacer(modifier = Modifier.height(21.5.dp))
                        Text(
                            text="ALLOW",
                            textAlign = TextAlign.Center,
                            fontSize = 14.sp,
                            fontFamily = roboto,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.clickable {
                                launcher.launch(android.Manifest.permission.READ_CONTACTS) // Launch permission request when clicked
                            }


                        )
                        Spacer(modifier = Modifier.height(21.5.dp))
                        Divider(
                            color = appColor,
                            thickness = 1.dp
                        )
                        Spacer(modifier = Modifier.height(21.5.dp))
                        Text(
                            text="DON'T ALLOW",
                            textAlign = TextAlign.Center,
                            fontSize = 14.sp,
                            fontFamily = roboto,
                            fontWeight = FontWeight.SemiBold,


                            )
                        Spacer(modifier = Modifier.height(21.5.dp))
                    }

            }

        }
    }





}







