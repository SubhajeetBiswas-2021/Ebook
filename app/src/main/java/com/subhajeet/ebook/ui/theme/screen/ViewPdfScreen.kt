package com.subhajeet.ebook.ui.theme.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.rizzi.bouquet.ResourceType
import com.rizzi.bouquet.VerticalPDFReader
import com.rizzi.bouquet.VerticalPdfReaderState

@Composable
fun ViewPdfScreen(url:String,navController: NavController) {

    val pdfVerticallReaderState = VerticalPdfReaderState(
        resource = ResourceType.Remote(url),
        isZoomEnable = true
    )

    Column {
        VerticalPDFReader(
            state = pdfVerticallReaderState,
            modifier = Modifier.fillMaxSize().background(color = Color.Gray)
        )
    }
}