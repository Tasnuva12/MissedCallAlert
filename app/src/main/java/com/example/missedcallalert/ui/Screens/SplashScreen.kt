package com.example.missedcallalert.ui.Screens


import android.Manifest
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.ban.otptextfield.OtpTextField
import com.example.missedcallalert.R
import com.example.missedcallalert.Screen
import com.example.missedcallalert.data.Country
import com.example.missedcallalert.ui.Components.CustomButton
import com.example.missedcallalert.ui.Components.CustomText
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
fun SplashScreen(
    navController: NavController?=null,
    viewModel: SplashScreenViewModel= hiltViewModel()
){

    val appConfigState=viewModel.appConfigFlow.collectAsState()
    val loginState = viewModel.loginFlow.collectAsState()
    val permissionState =remember{ mutableStateOf(false) }
    var showDialog= remember{ mutableStateOf(false) }
    val context = LocalContext.current

    var isInternetConnected by remember { mutableStateOf(true) }
    val snackbarHostState = remember { SnackbarHostState()






}

@Composable
fun SplashScreenBody(modifier: Modifier = Modifier, navController: NavHostController) {
    val splashScreenViewModel: SplashScreenViewModel = viewModel()


   //SplashScreen UI
    Box(modifier = modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState()), contentAlignment = Alignment.Center) {
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
            CustomText(
                text = "MISSED CALL",
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
                    fontSize = 25.sp
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
                Spacer(modifier = Modifier.height(0.dp))

                CountryPhoneInput(
                    viewModel = splashScreenViewModel


                )
            }


        }




    }





//function for the Dropdown menu and Phone NO field
@Composable
fun CountryPhoneInput(
    viewModel: SplashScreenViewModel,

){
    val viewModelOtp: OtpViewModel = hiltViewModel()

    val context = LocalContext.current

    //dialog box state
    var  privacyPolicyDialogBox by remember { mutableStateOf(false) }
    //variables
    var expanded by remember { mutableStateOf(false) }
    val selectedCode by viewModel.country.observeAsState(countries.first())
    val phoneNo by viewModel.phoneNumber.observeAsState("")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(1.dp)
                .height(70.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
           Box(  modifier = Modifier
               .background(Color.Transparent) //  any other color
               .width(105.dp)
               .height(40.dp)
               .border(BorderStroke(1.dp, Color.White), RoundedCornerShape(8.dp))){
               Row(
                   modifier = Modifier
                       .fillMaxWidth()
                       .padding(8.dp)
                       .clickable { expanded = true },
                   verticalAlignment = Alignment.CenterVertically
               ){
                   Image(
                       painter = painterResource(id = R.drawable.ic_arrow_down),
                       contentDescription = "Dropdown Icon",
                       modifier = Modifier
                           .size(12.dp)
                           .clip(RoundedCornerShape(4.dp)),

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
            Box(
//                modifier = Modifier
//
//                    .fillMaxWidth()
//                    .height(40.dp),
//
//                shape = RoundedCornerShape(8.dp),
//                border = BorderStroke(1.dp, Color.White)

                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .background(Color.Transparent)
                    .border(BorderStroke(1.dp, Color.White), RoundedCornerShape(8.dp))

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
        CustomButton(
             modifier = Modifier,

            text="Generate OTP",
            onClick = {
                if (phoneNo != "") {

                    viewModelOtp.requestOtp(phoneNo, selectedCode)


                } else {
                    Toast.makeText(context, "Enter your phone number first.", Toast.LENGTH_SHORT).show()
                }
            }

         )

        }

    if(privacyPolicyDialogBox){
        Dialog(onDismissRequest = { privacyPolicyDialogBox=false }) {
            Card(
                modifier = Modifier
                    .width(370.dp)
                    .height(677.dp)
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
            ) {
                   Text("this is alert box")
            }
        }

    }





}









