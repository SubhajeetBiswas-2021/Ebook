package com.subhajeet.ebook.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.subhajeet.ebook.common.ResultState
import com.subhajeet.ebook.data.models.bookModel
import com.subhajeet.ebook.repo.Repo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MyViewModels @Inject constructor(private val repo: Repo) : ViewModel() {

    private val _getAllBookState = MutableStateFlow(GetAllBookState())   //if this is used then we change the state from outside only so make it a private variable first and then access it via a public variable
    val getAllBookState = _getAllBookState.asStateFlow()   //it is the public variable and we will get the as soon we access this

    init {
        getAllBooks()
    }
    fun getAllBooks(){
        viewModelScope.launch(Dispatchers.IO) {
            repo.getAllBooks().collect{
                when(it){
                    is ResultState.Loading ->{
                        _getAllBookState.value= GetAllBookState(
                            isLoading = true
                        )
                    }
                    is ResultState.Error ->{ //if getting error passing to UI
                        _getAllBookState.value = GetAllBookState(
                            isLoading = false,
                            error = it.message,
                        )

                    }
                    is ResultState.Success ->{
                        _getAllBookState.value= GetAllBookState(
                            isLoading = false,
                            success = it.data
                        )
                    }
                }
            }
        }
    }
}

data class GetAllBookState(
    val isLoading:Boolean=false,
    val success: List<bookModel> = emptyList(),
    val error:String?=null
)