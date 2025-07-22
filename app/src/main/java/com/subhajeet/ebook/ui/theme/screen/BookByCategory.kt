package com.subhajeet.ebook.ui.theme.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.subhajeet.ebook.viewModel.MyViewModels

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookByCategory(modifier: Modifier = Modifier,categoryName: String,viewModels: MyViewModels = hiltViewModel(), navController: NavController) {

    // Call ViewModel function when screen is first shown
    LaunchedEffect(categoryName) {
        viewModels.getBookByCategory(categoryName)
    }
    var scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    val getBookByCategoryState = viewModels.getBookByCategoryState.collectAsState()
    Scaffold(modifier = Modifier.fillMaxSize().nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(categoryName)
                },
                scrollBehavior = scrollBehavior
            )

        })
    { innerPadding ->
        when {
            getBookByCategoryState.value.isLoading -> {
                CircularProgressIndicator()
            }

            getBookByCategoryState.value.error != null -> {
                Text(text = "Error: ${getBookByCategoryState.value.error}", modifier = modifier)
            }

            getBookByCategoryState.value.success.isNotEmpty() -> {
                LazyColumn(modifier=Modifier.padding(0.dp,100.dp,0.dp,0.dp)) {
                    items(getBookByCategoryState.value.success) {
                        EachCard(
                            title = it.bookName,
                            author = it.bookAuthor,
                            bookImage = it.bookImage
                        )
                    }
                }
            }
        }
    }
}


//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EachCard(title: String, author: String, bookImage: String) {
    Card(modifier = Modifier
        .padding(16.dp,28.dp,16.dp,0.dp)
        .height(140.dp)) {



        Box(modifier = Modifier.fillMaxSize()){
            /*  Image(
                  imageVector = Icons.Default.Face,
                  contentDescription = null,
                  modifier = Modifier.fillMaxHeight().padding(18.dp).height(80.dp).width(49.dp)
              )*/
            AsyncImage(
                model =bookImage,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(110.dp)
                    .clip(RoundedCornerShape(7.dp))
                    .padding(1.dp, 25.dp, 0.dp, 0.dp)
            )

            Spacer(modifier= Modifier.width(10.dp))

            Text(text =title, fontSize = 20.sp, textAlign = TextAlign.Center, modifier = Modifier
                .align(
                    Alignment.TopCenter
                )
                .padding(6.dp, 0.dp, 0.dp, 0.dp), fontStyle = FontStyle.Italic,
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