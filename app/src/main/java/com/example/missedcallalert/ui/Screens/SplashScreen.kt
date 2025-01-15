package com.example.missedcallalert.ui.Screens


import android.app.AlertDialog
import android.text.Html
import android.text.method.LinkMovementMethod
import android.util.Log
import android.widget.TextView
import android.widget.Toast
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.missedcallalert.AppConfigurationResponse
import com.example.missedcallalert.InputType
import com.example.missedcallalert.NetworkMonitor
import com.example.missedcallalert.R
import com.example.missedcallalert.Resource
import com.example.missedcallalert.Screen
import com.example.missedcallalert.data.Country
import com.example.missedcallalert.isValid
import com.example.missedcallalert.ui.Components.CustomButton
import com.example.missedcallalert.ui.Components.CustomText
import com.example.missedcallalert.viewModels.OtpViewModel
import com.example.missedcallalert.viewModels.SplashScreenViewModel
import com.google.gson.Gson
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


val roboto = FontFamily(
    Font(R.font.archivo_condensed_semibold),
    Font(R.font.archivo_condensed_bold),
    Font(R.font.archivo_condensed_regular)
)

val countries = listOf(
    Country("+880", R.drawable.bangladesh, "Bangladesh"),
    Country("+91", R.drawable.ic_indian_flag, "India")
)


@OptIn(ExperimentalMaterial3Api::class)
//UI of splashscreen

@Composable
fun SplashScreen(
    navController: NavController? = null,
    modifier: Modifier,
    viewModel: SplashScreenViewModel = hiltViewModel(),
    otpViewModel: OtpViewModel = hiltViewModel(),




) {

    val appConfigState = otpViewModel.appConfigFlow.collectAsState()
    val loginState = otpViewModel.loginFlow.collectAsState()
    val permissionState = remember { mutableStateOf(false) }
    var showDialog = remember { mutableStateOf(false) }
    val context = LocalContext.current

    var isInternetConnected by remember { mutableStateOf(true) }
    val snackbarHostState = remember {
        SnackbarHostState()


    }
    NetworkMonitor { isConnected ->
        isInternetConnected = isConnected
        Log.d("internet", "isInternetConnected: $isInternetConnected")
        if (!isConnected) {
            LaunchedEffect(Unit) {
                delay(200)
                snackbarHostState.showSnackbar(
                    "No Internet connection",
                    duration = SnackbarDuration.Long
                )
            }
        }
    }

    if (isInternetConnected) {
        Log.d("internet", "called app config ")
        LaunchedEffect(key1 = Unit) {
            otpViewModel.getAppConfig()
        }
    }
    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { },
            confirmButton = {},
            text = {
                Box(
                    modifier = Modifier
                        .background(color = Color.White)
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            ),
            containerColor = Color.White
        )
    }
    LaunchedEffect(key1 = Unit) {

        // Attempt login if password is not blank
        if (otpViewModel.mPref.password.toString().isNotBlank()) {
            showDialog.value = true
            otpViewModel.login()
        }
    }

    if (otpViewModel.mPref.password.toString().isNotBlank()) {
        if (permissionState.value) {
            if (otpViewModel.mPref.toString().isBlank()) {
                val jsonData = otpViewModel.mPref.setAppConfigData
                if (jsonData != "") {
                    val appConfigData =
                        Gson().fromJson(jsonData, AppConfigurationResponse.Data::class.java)
                     ShowDialog(htmlContent = appConfigData.privacyPolicy ?: "")
                }
            }
        }
    }

    SplashScreenBody(
        navController, modifier, viewModel, otpViewModel, isInternetConnected,snackbarHostState
    )

}

@Composable
fun SplashScreenBody(

    navController: NavController?,
    modifier: Modifier = Modifier,
    viewModel: SplashScreenViewModel,
    otpViewModel: OtpViewModel,
    isInternetConnected: Boolean,
    snackbarHostState: SnackbarHostState
) {
    if (otpViewModel.mPref.password.toString().isNotBlank()) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()), contentAlignment = Alignment.Center
        ) {
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
                Spacer(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .height(8.dp)
                        .fillMaxWidth()
                )
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
            }
        }
    } else {
        //SplashScreen UI
        Box(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()), contentAlignment = Alignment.Center
        ) {
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
                Spacer(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .height(8.dp)
                        .fillMaxWidth()
                )
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
                    navController, viewModel, isInternetConnected, snackbarHostState,otpViewModel


                )
            }


        }
    }

}


//function for the Dropdown menu and Phone NO field
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryPhoneInput(
    navController: NavController? = null,
    viewModel: SplashScreenViewModel,
    isInternetConnected: Boolean,
    snackbarHostState: SnackbarHostState,
    viewModelOtp:OtpViewModel


) {


    val context = LocalContext.current

    //dialog box state
    var privacyPolicyDialogBox by remember { mutableStateOf(false) }
    //variables
    var expanded by remember { mutableStateOf(false) }
    val selectedCode by viewModel.country.observeAsState(countries.first())
    val phoneNo by viewModel.phoneNumber.observeAsState("")

    val showDialog = remember { mutableStateOf(false) }
    val registrationState=viewModelOtp.otpResponse.collectAsState()
    LaunchedEffect (registrationState.value){
      when(val state=registrationState.value){
          is Resource.Loading -> {
              Log.d("SplashScreen","Loading")
          }

          is Resource.Success -> {
            if(state.data?.status==1){
                Toast.makeText(context,state.data?.data?.message,Toast.LENGTH_LONG).show()
                showDialog.value=false
                viewModelOtp.mPref.phoneNumber=phoneNo
                //need to navigate to the otp verifiy screen here
                navController?.navigate(Screen.OtpVerificationScreen){
                    popUpTo(Screen.SplashScreen)
                    {
                        inclusive=true

                    }
                }




            }
              else {
                  showDialog.value=false
                Toast.makeText(context, "Failed, Try again.", Toast.LENGTH_LONG).show()
                Log.d("SplashScreen", "Failed, Try again.")

            }
          }
          is Resource.Failure ->{

              showDialog.value=false
              Toast.makeText(context, "Failed, Try again.", Toast.LENGTH_LONG).show()
              Log.d("SplashScreen", "Failed, Try again.")

          }

      }
    }

    if(showDialog.value){
        AlertDialog(
            onDismissRequest = { },
            confirmButton = {},
            text = {
                Box(
                    modifier = Modifier
                        .background(color = Color.White)
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            ),
            containerColor = Color.White
        )
    }

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
            Box(
                modifier = Modifier
                    .background(Color.Transparent) //  any other color
                    .width(105.dp)
                    .height(40.dp)
                    .border(BorderStroke(1.dp, Color.White), RoundedCornerShape(8.dp))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable { expanded = true },
                    verticalAlignment = Alignment.CenterVertically
                ) {
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
            ) {
                val scrollState = rememberScrollState()

                Column(
                    modifier = Modifier
                        .heightIn(max = 200.dp)
                        .verticalScroll(scrollState)
                ) {
                    countries.forEach { item ->
                        DropdownMenuItem(
                            onClick = {
                                viewModel.setCountryCode(item)
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
                    onValueChange = { newPhoneNo -> viewModel.setPhoneNumber(newPhoneNo) },
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


        val coroutineScope= rememberCoroutineScope()
        //Code for the button
        CustomButton(


            text = "Generate OTP",
            onClick = {
               if(isInternetConnected){
                   val trimmedPhoneNo=selectedCode.code +phoneNo.trim()
                   if(trimmedPhoneNo.isBlank()){
                       Toast.makeText(context,"Phone Number is required",Toast.LENGTH_LONG).show()
                   }
                   else if(!trimmedPhoneNo.isValid(InputType.PHONE)){
                       Toast.makeText(context,"Phone Number is not valid",Toast.LENGTH_LONG).show()
                   }
                   else{
                       showDialog.value=true
                       viewModelOtp.requestOtp(
                           trimmedPhoneNo,selectedCode
                       )
                   }
               }
                   else{
                       coroutineScope.launch{
                           snackbarHostState.showSnackbar(
                               message="Please check your internet connection",
                               actionLabel = "Retry",
                               duration = SnackbarDuration.Short
                           )
                       }

               }
            },

            modifier = Modifier
                .width(280.dp)
                .height(50.dp)

        )

    }

    if (privacyPolicyDialogBox) {
        Dialog(onDismissRequest = { privacyPolicyDialogBox = false }) {
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

@Composable
fun ShowDialog(htmlContent:String) {
    val  openDialog= remember{mutableStateOf(false)}
    if(openDialog.value){
       Dialog(
           onDismissRequest = {
               openDialog.value=false
           }

       )

           {
               Box(
                   modifier = Modifier
                       .fillMaxWidth()
                       .wrapContentHeight()
                       .background(Color.White, shape = RoundedCornerShape(8.dp))
                       .padding(16.dp)
               ) {
                   Column(
                       modifier = Modifier.padding(16.dp)
                   ) {
                       androidx.compose.material.Text(
                           text = "Privacy Policy",
                           modifier = Modifier.padding(bottom = 8.dp)
                       )

                       AndroidView(
                           modifier = Modifier
                               .fillMaxSize()
                               .background(Color.White)
                               .padding(16.dp),
                           factory = { context ->
                               TextView(context).apply {
                                   text = Html.fromHtml(htmlContent, Html.FROM_HTML_MODE_COMPACT)
                                   movementMethod = LinkMovementMethod.getInstance()
                               }
                           }
                       )
                   }

                   Row(
                       modifier = Modifier
                           .fillMaxWidth()
                           .align(Alignment.BottomCenter)
                           .padding(top = 16.dp),
                       horizontalArrangement = Arrangement.SpaceBetween
                   ) {
                       // Dismiss Button
                       Button(
                           onClick = { openDialog.value = false },
                           modifier = Modifier.padding(end = 8.dp)
                       ) {
                           androidx.compose.material.Text("Cancel")
                       }

                       // Confirm Button
                       Button(
                           onClick = { openDialog.value = false }
                       ) {
                           androidx.compose.material.Text("OK")
                       }
                   }
               }
           }

       }
    }









