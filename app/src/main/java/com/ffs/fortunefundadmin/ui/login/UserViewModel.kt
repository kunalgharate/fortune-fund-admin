package com.ffs.fortunefundadmin.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ffs.fortunefundadmin.domain.model.Response
import com.ffs.fortunefundadmin.domain.model.AppUser
import com.ffs.fortunefundadmin.domain.model.Wallet
import com.ffs.fortunefundadmin.domain.use_case.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserViewModel @Inject constructor(private val usecas: UserUseCases): ViewModel() {

  //  private lateinit var deleteBookResponse: Response<Void?>
     lateinit var addUserResponse: Response<Void?>
   // var respons: MutableLiveData<Response<List<Book>>> = MutableLiveData(Response.Loading)
    init {
    //    getBooks()
    }

//    private fun getBooks() = viewModelScope.launch {
//        useCases.getBooks().collect { response ->
//            respons.value= response;
//            ;
//            Log.d("MTTTTT", "getBooks: $response")
//        }
//
//    }

    fun addUser(appUser: AppUser) = viewModelScope.launch {
        usecas.addUser(appUser).collect { response ->
            addUserResponse = response

            usecas.addWallet(Wallet("",0,"initial balance","",appUser.id))
            Log.d("loghh", "addUser: "+response)
        }
    }

//    fun deleteBook(bookId: String) = viewModelScope.launch {
//        useCases.deleteBook(bookId).collect { response ->
//            deleteBookResponse = response
//        }
//    }

}