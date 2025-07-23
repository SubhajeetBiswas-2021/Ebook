package com.subhajeet.ebook.ui.theme.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.subhajeet.ebook.data.models.BookCategory
import com.subhajeet.ebook.ui.theme.screen.nav.Routes
import com.subhajeet.ebook.viewModel.MyViewModels


@Composable
fun CategoriesScreen(viewModels: MyViewModels = hiltViewModel(), navController: NavController) {
    /*Text(text = "Categories",modifier= Modifier
        .fillMaxHeight()
        .fillMaxWidth())*/
    val getAllCategoryState = viewModels.getAllCategoryState.collectAsState()
    val context = LocalContext.current
    when{
        getAllCategoryState.value.isLoading ->{
            Box{
                CircularProgressIndicator()
            }
        }
        getAllCategoryState.value.error !=null-> {
            Toast.makeText(context,"Error: ${getAllCategoryState.value.error}",Toast.LENGTH_SHORT).show()
        }
        getAllCategoryState.value.success != null ->{
            LazyVerticalGrid(GridCells.Fixed(2), modifier = Modifier.fillMaxSize()) {

                items(getAllCategoryState.value.success){
                    CategoryCard(

                        imageUrl = it.categoryImageUrl,
                        CategoryName = it.categoryName,
                        onClick = {
                            navController.navigate(
                                Routes.BookByCategory(
                                    categoryName = it.categoryName
                                )
                            )
                        }
                    )
                }

            }
        }
    }
}

//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CategoryCard(imageUrl:String,CategoryName:String, onClick:()-> Unit) {
    Card(modifier=Modifier.height(200.dp).padding(10.dp,6.dp,11.dp,9.dp).clickable {
        onClick()
    }, elevation = CardDefaults.cardElevation(8.dp),) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter){

           /* Image(
                imageVector = Icons.Default.Face,
                contentDescription = null,
                modifier = Modifier.fillMaxHeight().padding(18.dp).height(80.dp).width(49.dp)
            )*/
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.height(156.dp).width(180.dp)
            )

            Spacer(modifier = Modifier.height(6.dp))


            Text(
                text = CategoryName,
                fontSize = 16.sp,
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.End,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(end = 12.dp, bottom = 8.dp)
            )
        }
    }
}

