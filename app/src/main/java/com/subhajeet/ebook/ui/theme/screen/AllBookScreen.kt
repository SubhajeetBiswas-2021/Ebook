package com.subhajeet.ebook.ui.theme.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AllBookScreen() {

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun eachCard() {
   Card(modifier = Modifier.padding(8.dp,28.dp,0.dp,0.dp).height(100.dp)) {



       Box(modifier = Modifier.fillMaxSize()){
               Image(
                   imageVector = Icons.Default.Face,
                   contentDescription = null,
                   modifier = Modifier.fillMaxHeight().padding(18.dp).height(80.dp).width(49.dp)
               )

               Spacer(modifier=Modifier.width(8.dp))

                Text(text =" Title", fontSize = 30.sp, textAlign = TextAlign.Center, modifier = Modifier.align(
                       Alignment.TopCenter))

           Text(
               text = "Author",
               fontSize = 16.sp,
               fontStyle = FontStyle.Italic,
               textAlign = TextAlign.End,
               modifier = Modifier
                   .align(Alignment.BottomEnd)
                   .padding(end = 12.dp, bottom = 8.dp)
           )


           }
   }


}
