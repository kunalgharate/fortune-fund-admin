package com.ffs.fortunefundadmin.domain.repository

import com.ffs.fortunefundadmin.domain.model.AppUser
import kotlinx.coroutines.flow.Flow
import com.ffs.fortunefundadmin.domain.model.InvestmentPlan
import com.ffs.fortunefundadmin.domain.model.Response

interface InsurancePlanRepository {

    fun getInvestmentPlanFromFirestore(): Flow<Response<List<InvestmentPlan>>>


    fun getProfileFromFirestore(userId:String): Flow<Response<AppUser>>


   // suspend fun addBookToFirestore(title: String, author: String): Flow<Response<Void?>>

    suspend fun deleteBookFromFirestore(bookId: String): Flow<Response<Void?>>
}