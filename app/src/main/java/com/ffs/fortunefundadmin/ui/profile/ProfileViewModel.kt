package com.ffs.fortunefundadmin.ui.profile

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ffs.fortunefundadmin.domain.model.InvestmentPlan
import com.ffs.fortunefundadmin.domain.model.Response
import com.ffs.fortunefundadmin.domain.use_case.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(private val useCases: UseCases): ViewModel() {

    private lateinit var deleteBookResponse: Response<Void?>
    private lateinit var addBookResponse: Response<Void?>
    var respons: MutableLiveData<Response<List<InvestmentPlan>>> = MutableLiveData(Response.Loading)
    init {
        getBooks()
    }

    private fun getBooks() = viewModelScope.launch {
        useCases.getBooks().collect { response ->
            respons.value= response;
            ;
            Log.d("MTTTTT", "getBooks: $response")
        }

    }

//    fun addBook(title: String, author: String) = viewModelScope.launch {
//        useCases.addBook(title, author).collect { response ->
//            addBookResponse = response
//        }
//    }

    fun deleteBook(bookId: String) = viewModelScope.launch {
        useCases.deleteBook(bookId).collect { response ->
            deleteBookResponse = response
        }
    }

}