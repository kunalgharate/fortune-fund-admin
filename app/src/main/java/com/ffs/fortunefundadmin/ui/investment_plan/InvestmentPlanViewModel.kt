package com.ffs.fortunefundadmin.ui.investment_plan

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ffs.fortunefundadmin.domain.model.AppUser
import com.ffs.fortunefundadmin.domain.model.InvestmentPlan
import com.ffs.fortunefundadmin.domain.model.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

import com.ffs.fortunefundadmin.domain.use_case.UseCases
import javax.inject.Inject

@HiltViewModel
class InvestmentPlanViewModel @Inject constructor(private val useCases: UseCases): ViewModel() {

    private lateinit var deleteBookResponse: Response<Void?>
    private lateinit var addBookResponse: Response<Void?>
    var respons: MutableLiveData<Response<List<InvestmentPlan>>> = MutableLiveData(Response.Loading)
    var appUser: MutableLiveData<Response<AppUser>> = MutableLiveData(Response.Loading)
    init {
        getBooks()
        getProfile()
    }

    private fun getBooks() = viewModelScope.launch {
        useCases.getBooks().collect { response ->
           respons.value= response;
;
            Log.d("MTTTTT", "getBooks: $response")
        }

    }



    private fun getProfile() = viewModelScope.launch {
        useCases.getProfile()?.collect { response ->
            appUser.value= response;
            ;
            Log.d("MTTTTT", "appUser: $response")
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