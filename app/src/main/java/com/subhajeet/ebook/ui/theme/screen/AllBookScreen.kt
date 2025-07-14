package com.subhajeet.ebook.ui.theme.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AllBookScreen() {

}

@Preview(showBackground = true, showSystemUi = true
)
@Composable
fun eachCard() {
   Card(modifier = Modifier.fillMaxWidth().padding(8.dp,28.dp).height(100.dp)) {

       Row(modifier = Modifier) {
           Image(
               imageVector = Icons.Default.Face,
               contentDescription = null,
               modifier = Modifier.fillMaxHeight().padding(18.dp).height(80.dp).width(49.dp)
           )
           Column(modifier = Modifier) {

            Text(text =" Title", fontSize = 30.sp, textAlign = TextAlign.Justify)

               Spacer(modifier = Modifier.height(8.dp))

               Text(text ="Author", fontSize = 20.sp, textAlign = TextAlign.Center)
           }
       }
   }


}