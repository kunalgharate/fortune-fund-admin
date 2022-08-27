package com.ffs.fortunefundadmin.repository

import com.ffs.fortunefundadmin.core.Constants.INVESTMENT_PLAN
import com.ffs.fortunefundadmin.core.Constants.NAME
import com.ffs.fortunefundadmin.core.Constants.USERS
import com.ffs.fortunefundadmin.domain.model.AppUser
import com.ffs.fortunefundadmin.domain.model.InvestmentPlan
import com.ffs.fortunefundadmin.domain.model.Response
import com.ffs.fortunefundadmin.domain.model.Response.*
import com.ffs.fortunefundadmin.domain.repository.InsurancePlanRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton

class InvestmentPlanRepositoryImpl @Inject constructor(
    private val investmentPlanRef: FirebaseFirestore
) : InsurancePlanRepository {

    override fun getInvestmentPlanFromFirestore() = callbackFlow {
        val snapshotListener = investmentPlanRef.collection(INVESTMENT_PLAN).orderBy(NAME)
            .addSnapshotListener { snapshot, e ->
                val response = if (snapshot != null) {
                    val books = snapshot.toObjects(InvestmentPlan::class.java)
                    Success(books)
                } else {
                    Error(e?.message ?: e.toString())
                }
                trySend(response).isSuccess
            }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override fun getProfileFromFirestore(userid: String): Flow<Response<AppUser>> = callbackFlow {

        val snapshotListener =
            investmentPlanRef.collection(USERS).whereEqualTo("id", userid).orderBy(NAME)
                .addSnapshotListener { snapshot, e ->

                    val response = if (snapshot != null) {

                        Success(snapshot.toObjects<AppUser>().first())

                        //  val appUsers = snapshot.toObjects(AppUser::class.java)
                        //  Success(appUsers)
                    } else {
                        Error(e?.message ?: e.toString())
                    }
                    trySend(response).isSuccess
                }
        awaitClose {
            snapshotListener.remove()
        }
    }


//    override suspend fun addBookToFirestore(investmentPlan: InvestmentPlan) = flow {
//        try {
//            emit(Loading)
//            val id = investmentPlanRef.collection(BOOKS).document().id
//
//            val addition = investmentPlanRef.document(id).set(investmentPlan).await()
//            emit(Success(addition))
//        } catch (e: Exception) {
//            emit(Error(e.message ?: e.toString()))
//        }
//    }

    override suspend fun deleteBookFromFirestore(bookId: String) = flow {
        try {
            emit(Loading)
            val deletion =
                investmentPlanRef.collection(INVESTMENT_PLAN).document(bookId).delete().await()
            emit(Success(deletion))
        } catch (e: Exception) {
            emit(Error(e.message ?: e.toString()))
        }
    }
}