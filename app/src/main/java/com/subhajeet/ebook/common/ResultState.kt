package com.subhajeet.ebook.common

import androidx.core.app.NotificationCompat.MessagingStyle.Message

sealed class ResultState<out T> {
//sealed class stores a single type of data
    data class Success<out T>(val data:T):ResultState<T>()

    data class Error<T>(val message: Message):ResultState<T>()

    object Loading:ResultState<Nothing>()


}