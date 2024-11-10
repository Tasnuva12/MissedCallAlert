package com.example.missedcallalert

import android.os.Bundle
import android.provider.FontsContract.Columns
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.missedcallalert.ui.theme.MissedCallAlertTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MissedCallAlertTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Home(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Home(name: String, modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.background),
            contentDescription = "background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()

        )
        Column(modifier = Modifier.background(Color.Gray).fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            Column(modifier = Modifier.background(Color.Blue).fillMaxWidth().padding(top=149.dp,start=83.dp,end=84.dp), horizontalAlignment = Alignment.CenterHorizontally){
                Image(
                    painterResource(R.drawable.vector),
                    contentDescription = "missedcall",
                    modifier = Modifier
                        .height(106.15.dp)
                        .width(136.15.dp),

                    )
                Spacer(modifier=Modifier.padding(top=16.dp).height(8.dp).fillMaxWidth())
                Text(text = "Missed Call", color= Color.White, fontSize = 30.sp, maxLines = 1)

            }



        }


    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MissedCallAlertTheme {
        Home("Android")
    }
}