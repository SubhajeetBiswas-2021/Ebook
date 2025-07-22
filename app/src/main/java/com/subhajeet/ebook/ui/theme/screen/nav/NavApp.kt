package com.subhajeet.ebook.ui.theme.screen.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.subhajeet.ebook.ui.theme.screen.BookByCategory
import com.subhajeet.ebook.ui.theme.screen.CategoriesScreen
import com.subhajeet.ebook.ui.theme.screen.TabScreen
import com.subhajeet.ebook.ui.theme.screen.ViewPdfScreen

@Composable
fun NavApp() {

    val navController = rememberNavController()


    NavHost(
        navController=navController,
        startDestination = Routes.TabScreen
    ){
        composable<Routes.TabScreen> {
            TabScreen(navController = navController) // ✅ Pass navController to tab screen
        }

        composable<Routes.CategoriesScreen> {
            CategoriesScreen(navController=navController)
        }

        composable<Routes.BookByCategory> {
            val data = it.toRoute<Routes.BookByCategory>()
            BookByCategory(navController =navController,
                categoryName= data.categoryName ?: ""
            )
        }

        composable<Routes.ViewPdfScreen> {
            val data = it.toRoute<Routes.ViewPdfScreen>()
            ViewPdfScreen(
                navController = navController,
                url = data.url,
                bookName = data.bookName ?:""
            )
        }
    }

}