package com.subhajeet.ebook.ui.theme.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.rizzi.bouquet.ResourceType
import com.rizzi.bouquet.VerticalPDFReader
import com.rizzi.bouquet.VerticalPdfReaderState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewPdfScreen(url:String,bookName:String,navController: NavController) {

    var scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()  //For Top AppBar

    val pdfVerticallReaderState = remember {
        VerticalPdfReaderState(
            resource = ResourceType.Remote(url),
            isZoomEnable = true
        )
    }

    /*Column {
        VerticalPDFReader(
            state = pdfVerticallReaderState,
            modifier = Modifier.fillMaxSize().background(color = Color.Gray)
        )
    }*/
    Scaffold(modifier = Modifier.fillMaxSize().nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(bookName)
                },
                scrollBehavior = scrollBehavior
            )

        })
    {innerPadding ->
    Box(modifier = Modifier.fillMaxSize()) {
        // Always render the reader first
        VerticalPDFReader(
            state = pdfVerticallReaderState,
            modifier = Modifier.fillMaxSize()
        )

        // Then conditionally render the loader OVER it
        if (!pdfVerticallReaderState.isLoaded && pdfVerticallReaderState.error == null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0x88000000)), // semi-transparent overlay
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator(
                    color = Color.Blue,
                    strokeWidth = 5.dp
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    "Loading PDF...",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
            }
        }
        // ❌ Optional: show error if PDF failed to load
        if (pdfVerticallReaderState.error != null) {
            Text(
                text = "Failed to load PDF",
                color = Color.Red,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

}

