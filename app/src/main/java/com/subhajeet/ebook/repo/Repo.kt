package com.subhajeet.ebook.repo

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import com.subhajeet.ebook.common.ResultState
import com.subhajeet.ebook.data.models.BookCategory
import com.subhajeet.ebook.data.models.bookModel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject


//Followed documentation:-(https://firebase.google.com/docs/database/android/read-and-write#kotlin_4 ) to read data from firebase
class Repo @Inject constructor(private val firebaseDatabase: FirebaseDatabase){


    fun getAllBooks(): Flow<ResultState<List<bookModel>>> = callbackFlow {
    //call back is a thing which updates us as any changes happen like in google maps and while using firebase callback is required
    //In flow if we have to send anything we send it through emit
    //In callbackflow we send anything through trysend ,trysend is used for emission
        trySend(ResultState.Loading)  // here we first emit loading

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                //we want to get
                var items:List<bookModel> = emptyList()
                //now putting the data on the variable items
                items = dataSnapshot.children.map {
                    it.getValue<bookModel>()!!
                }
                trySend(ResultState.Success(items))

            }

            override fun onCancelled(databaseError: DatabaseError) {
                trySend(ResultState.Error(databaseError.message))

            }
        }
        firebaseDatabase.reference.child("Books").addValueEventListener(postListener)  //We want to listen the Books child created in firebase

        awaitClose {   //it is important as the callbacks will get generated it will nonstop listen so to stop that it is required and if not given the app will crash
            close()
        }
    }

    fun getAllCategories():Flow<ResultState<List<BookCategory>>> = callbackFlow{
        trySend(ResultState.Loading)  // here we first emit loading

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                //we want to get
                var items:List<BookCategory> = emptyList()
                //now putting the data on the variable items
                items = dataSnapshot.children.map {
                    it.getValue<BookCategory>()!!
                }
                trySend(ResultState.Success(items))

            }

            override fun onCancelled(databaseError: DatabaseError) {
                trySend(ResultState.Error(databaseError.message))

            }
        }
        firebaseDatabase.reference.child("BookCategory").addValueEventListener(postListener)  //We want to listen the BookCategory child created in firebase

        awaitClose {   //it is important as the callbacks will get generated it will nonstop listen so to stop that it is required and if not given the app will crash
            close()
        }
    }

    fun getBookByCategory(categoryName :String):Flow<ResultState<List<bookModel>>> = callbackFlow {

        trySend(ResultState.Loading)

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                var items : List<bookModel> = emptyList()

                items = dataSnapshot.children.filter {
                    it.getValue<bookModel>()!!.bookCategory==categoryName
                }.map {
                    it.getValue<bookModel>()!!
                }

                trySend(ResultState.Success(items))

            }

            override fun onCancelled(databaseError: DatabaseError) {
                trySend(ResultState.Error(databaseError.message))

            }
        }
        firebaseDatabase.reference.child("Books").addValueEventListener(postListener)  //We want to listen the BookCategory child created in firebase

        awaitClose {   //it is important as the callbacks will get generated it will nonstop listen so to stop that it is required and if not given the app will crash
            close()
        }

    }





}