package com.subhajeet.ebook.ui.theme.screen.nav

import kotlinx.serialization.Serializable

sealed class Routes {

    @Serializable
    object TabScreen

    @Serializable
    object CategoriesScreen

    @Serializable
    object AllBookScreen

    @Serializable
    data class BookByCategory(
        val categoryName:String?= null
    )



}