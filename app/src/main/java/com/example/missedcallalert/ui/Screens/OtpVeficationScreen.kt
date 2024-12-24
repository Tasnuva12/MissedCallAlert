package com.example.missedcallalert.ui.Screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.ban.otptextfield.OtpTextField
import com.example.missedcallalert.R
import com.example.missedcallalert.api.OtpVerifictionAPI.OtpVerificationRepository
import com.example.missedcallalert.ui.Components.CustomButton
import com.example.missedcallalert.ui.Components.CustomText
import com.example.missedcallalert.viewModels.OtpViewModel
import com.example.missedcallalert.viewModels.SplashScreenViewModel

@Composable
fun OtpVerificationScreen(modifier: Modifier = Modifier,navController:NavHostController)
    {
        val splashScreenViewModel: SplashScreenViewModel = viewModel()
        val remainingTime by  splashScreenViewModel.timer.observeAsState(0) // Observe remaining time
        var username by remember { mutableStateOf(splashScreenViewModel.phoneNumber.value ?: "") }
        val timerActive = remainingTime > 0
        var otpValue by remember{ mutableStateOf("") }
        val  otpViewModel: OtpViewModel = hiltViewModel()
        val context = LocalContext.current
        val otpVerificationResult = otpViewModel.otpVerificationResult.observeAsState()
        LaunchedEffect(otpVerificationResult.value) {
            otpVerificationResult.value?.let { result ->
                if (result.isSuccess) {
                    // Show Toast for success
                    Toast.makeText(context, "OTP Verified Successfully!", Toast.LENGTH_SHORT).show()




                } else {
                    // Show Toast for failure
                    Toast.makeText(context, "OTP Verification Failed: ${result.exceptionOrNull()?.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }


        LaunchedEffect(key1 = timerActive) {
            if (!timerActive) {
                splashScreenViewModel.startTimer() // Start the timer when active
            }
        }

        Box(modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()), contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(R.drawable.background),
                contentDescription = "background",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()

            )
            CustomText(
                text = "Verify Phone Number",
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.SemiBold,
                fontFamily = roboto,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            CustomText(
                text = "We have sent you a verification code to ${splashScreenViewModel.country.value?.code.orEmpty()} *****${splashScreenViewModel.phoneNumber.value?.takeLast(1).orEmpty()}",
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                fontFamily = roboto,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(2.dp))
            CustomText(
                text = if (timerActive) {
                    "Resend Code after ${remainingTime}sec"

                } else  {
                    "You can resend the code now"
                },

                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                fontFamily = roboto,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(21.dp))
            OtpTextField(
                otpText = otpValue,
                onOtpTextChange = { value, otpInputFilled ->
                    otpValue = value
                    if (otpInputFilled) {
                        username = splashScreenViewModel.phoneNumber.value ?: ""

                    }
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            CustomButton(
                modifier = Modifier,
                text = "Verify",
                onClick = { otpViewModel.validateOtp(otpValue, username)},
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier= Modifier) {
                CustomText(
                    text = "No OTP yet?",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier,
                    fontFamily = roboto,
                )
                CustomText(
                    text = "Retry",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier,
                    fontFamily = roboto,

                    )
            }
        }


        }


