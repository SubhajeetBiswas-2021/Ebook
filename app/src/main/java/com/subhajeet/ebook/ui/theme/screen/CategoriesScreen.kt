package com.subhajeet.ebook.ui.theme.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CategoriesScreen() {
    /*Text(text = "Categories",modifier= Modifier
        .fillMaxHeight()
        .fillMaxWidth())*/
    Row(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        EachCard(modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.width(18.dp))
        EachCard(modifier = Modifier.weight(1f))
    }
}


@Composable
fun EachCard(modifier: Modifier = Modifier) {
    Card(modifier=modifier.height(200.dp), elevation = CardDefaults.cardElevation(8.dp)) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){

            Image(
                imageVector = Icons.Default.Face,
                contentDescription = null,
                modifier = Modifier.fillMaxHeight().padding(18.dp).height(80.dp).width(49.dp)
            )
        }
    }
}