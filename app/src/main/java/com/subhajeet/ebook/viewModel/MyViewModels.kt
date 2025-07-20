package com.subhajeet.ebook.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.subhajeet.ebook.common.ResultState
import com.subhajeet.ebook.data.models.BookCategory
import com.subhajeet.ebook.data.models.bookModel
import com.subhajeet.ebook.repo.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModels @Inject constructor(private val repo: Repo) : ViewModel() {

    private val _getAllBookState = MutableStateFlow(GetAllBookState())   //if this is used then we change the state from outside only so make it a private variable first and then access it via a public variable
    val getAllBookState = _getAllBookState.asStateFlow()   //it is the public variable and we will get the as soon we access this

    private val _getAllCategoryState = MutableStateFlow(GetAllCategoryState())
    val getAllCategoryState = _getAllCategoryState.asStateFlow()

    private val _getBookByCategoryState = MutableStateFlow(GetBookByCategoryState())
    val getBookByCategoryState = _getBookByCategoryState.asStateFlow()

    init {
        getAllBooks()
        getAllCategories()
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

    fun getAllCategories(){
        viewModelScope.launch(Dispatchers.IO) {
            repo.getAllCategories().collect{
                when(it){
                    is ResultState.Loading ->{
                        _getAllCategoryState.value= GetAllCategoryState(
                            isLoading = true
                        )
                    }
                    is ResultState.Error ->{ //if getting error passing to UI
                        _getAllCategoryState.value= GetAllCategoryState(
                            isLoading = false,
                            error = it.message,
                        )

                    }
                    is ResultState.Success ->{
                        _getAllCategoryState.value= GetAllCategoryState(
                            isLoading = false,
                            success = it.data
                        )
                    }
                }
            }
        }
    }

    fun getBookByCategory(categoryName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getBookByCategory(categoryName).collect{
                when(it){
                    is ResultState.Loading ->{
                        _getBookByCategoryState.value= GetBookByCategoryState(
                            isLoading = true
                        )
                    }
                    is ResultState.Error ->{ //if getting error passing to UI
                        _getBookByCategoryState.value = GetBookByCategoryState(
                            isLoading = false,
                            error = it.message,
                        )

                    }
                    is ResultState.Success ->{
                        _getBookByCategoryState.value= GetBookByCategoryState(
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

data class GetAllCategoryState(
    val isLoading:Boolean=false,
    val success: List<BookCategory> = emptyList(),
    val error:String?=null
)
data class GetBookByCategoryState(
    val isLoading:Boolean=false,
    val success: List<bookModel> = emptyList(),
    val error:String?=null
)