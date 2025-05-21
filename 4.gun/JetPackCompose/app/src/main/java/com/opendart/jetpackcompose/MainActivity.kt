package com.opendart.jetpackcompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.opendart.jetpackcompose.ui.theme.JetPackComposeTheme
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button

import androidx.compose.ui.Alignment
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun CustomText(text : String ){
    val context = LocalContext.current

    Text(modifier = Modifier
        .clickable {
            println("merhaba kotlin")
            Toast.makeText(context,"Merhaba Dünya",Toast.LENGTH_LONG).show()
        }
        .background(color = Color.Yellow)
        .padding(top = 10.dp, start = 1.dp, end = 1.dp, bottom = 20.dp)
        .width(150.dp)
        ,text = text
        ,color = Color.Black
        , fontSize = 24.sp
        , fontWeight =  FontWeight.Bold
        , textAlign =  TextAlign.Center
    )
}

@Composable
fun MainScreen() {
    // linear layout orienattion vertical
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        CustomText(text = "Merhaba Android")
        Spacer(modifier = Modifier.padding(5.dp))
        CustomText(text = "Merhaba Kotlin")
        Spacer(modifier = Modifier.padding(5.dp))
        CustomText(text = "Merhaba Compose")
        Spacer(modifier = Modifier.padding(5.dp))
        //orieantation horizontal bir linear layotu gibi
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            CustomText(text = "Test1")
            Spacer(modifier = Modifier.padding(5.dp))
            CustomText(text = "Test 2")
            Spacer(modifier = Modifier.padding(5.dp))
            CustomText(text = "Test 3")
        }

        Spacer(modifier = Modifier.padding(5.dp))

        Image(bitmap = ImageBitmap.imageResource(id = R.drawable.manzara)
            ,contentDescription = "manzara resmi")

        Spacer(modifier = Modifier.padding(5.dp))
        Button(onClick = {

        }) {
            Text("Kaydat")
        }

    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetPackComposeTheme {
        MainScreen()
    }
}