package com.subhajeet.ebook

import android.os.Bundle
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
import androidx.navigation.compose.rememberNavController
import com.subhajeet.ebook.ui.theme.EbookTheme
import com.subhajeet.ebook.ui.theme.screen.TabScreen
import com.subhajeet.ebook.ui.theme.screen.nav.NavApp
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EbookTheme {
                val navController = rememberNavController() // ✅ Create navController
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                 //   TabScreen(navController = navController)
                    NavApp()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EbookTheme {
        Greeting("Android")
    }
}