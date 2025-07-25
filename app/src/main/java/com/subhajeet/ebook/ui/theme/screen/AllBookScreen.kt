package com.subhajeet.ebook.ui.theme.screen
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.subhajeet.ebook.ui.theme.screen.nav.Routes
import com.subhajeet.ebook.viewModel.MyViewModels

@Composable
fun AllBookScreen(modifier: Modifier,viewModels: MyViewModels= hiltViewModel(), navController: NavController) {

    val getAllBookState = viewModels.getAllBookState.collectAsState()
    when{
        getAllBookState.value.isLoading ->{
            CircularProgressIndicator()
        }
        getAllBookState.value.error != null ->{
            Text(text="Error: ${getAllBookState.value.error}",modifier = modifier)
        }
        getAllBookState.value.success.isNotEmpty() ->{
            LazyColumn {
                items(getAllBookState.value.success){
                    eachCard(title = it.bookName, author = it.bookAuthor, bookImage = it.bookImage,onClick = {
                        navController.navigate(
                            Routes.ViewPdfScreen(
                                url = it.bookUrl,
                                bookName= it.bookName
                            )
                        )
                    })
                }
            }
        }
    }
}

//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun eachCard(title: String,author:String,bookImage:String ,onClick:()-> Unit) {
   Card(modifier = Modifier .padding(16.dp,28.dp,16.dp,0.dp).height(140.dp).clickable {
       onClick()
   }) {



       Box(modifier = Modifier.fillMaxSize()){
             /*  Image(
                   imageVector = Icons.Default.Face,
                   contentDescription = null,
                   modifier = Modifier.fillMaxHeight().padding(18.dp).height(80.dp).width(49.dp)
               )*/
           AsyncImage(
               model = bookImage,
               contentDescription = null,
               contentScale = ContentScale.Crop,
               modifier = Modifier.size(115.dp).clip(CircleShape).padding(6.dp,5.dp,0.dp,0.dp)
           )

               Spacer(modifier=Modifier.width(10.dp))

                Text(text =title, fontSize = 20.sp, textAlign = TextAlign.End, modifier = Modifier.align(
                       Alignment.TopEnd).padding(6.dp,0.dp,6.dp,0.dp), fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold )

           Text(
               text = author,
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


