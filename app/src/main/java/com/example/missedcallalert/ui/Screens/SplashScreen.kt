package com.example.missedcallalert.ui.Screens


import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.missedcallalert.R

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

            Image(
                painterResource(R.drawable.vector),
                contentDescription = "missedcall",
                alignment = Alignment.Center,
                modifier = Modifier
                    .height(106.15.dp)
                    .width(136.15.dp),

                )
            Spacer(modifier = Modifier.padding(top = 16.dp).height(8.dp).fillMaxWidth())
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
            Spacer(modifier = Modifier.height(80.dp))
            CountryPhoneInput()

        }




    }}

@Composable
fun CountryPhoneInput() {
    var selectedCountry by remember { mutableStateOf(countries[0]) } // Initial selected country
    var expanded by remember { mutableStateOf(false) } // Dropdown menu state

    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Country code dropdown
            Box {
                OutlinedTextField(
                    value = selectedCountry.code,
                    onValueChange = {},
                    readOnly = true,
                    modifier = Modifier.width(120.dp),
                    label = { Text("Country Code:") },
                    trailingIcon = {
                        IconButton(onClick = { expanded = !expanded }) {
                            Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
                        }
                    }
                )

                // Dropdown menu to select country
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    countries.forEach { country ->
                        DropdownMenuItem(
                            onClick = {
                                selectedCountry = country // Update selected country
                                expanded = false // Close the dropdown
                            },
                            modifier = Modifier.fillMaxWidth(), // You can modify as needed
                            leadingIcon = {
                                Image(
                                    painter = painterResource(id = country.flagRes),
                                    contentDescription = null,
                                    modifier = Modifier.size(24.dp)
                                )
                            },
                            text = {
                                Text(country.flagName) // This is the main text for the dropdown item
                            },
                            enabled = true, // You can disable the item if needed

                            contentPadding = PaddingValues(start = 8.dp) // Customize content padding if needed
                        )
                    }

                }
            }

            // Phone number input field
            OutlinedTextField(
                value = "", // You can replace with phone number state
                onValueChange = {},
                modifier = Modifier.weight(2f),
                label = { Text("Phone Number") }
            )
        }
    }
}



