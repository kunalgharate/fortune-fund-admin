package com.ffs.fortunefundadmin.repository

import com.ffs.fortunefundadmin.core.Constants


import kotlinx.coroutines.flow.flow
import com.ffs.fortunefundadmin.domain.model.Response.*
import com.ffs.fortunefundadmin.domain.model.AppUser
import com.ffs.fortunefundadmin.domain.model.Wallet
import com.ffs.fortunefundadmin.domain.repository.UserRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val userRef: FirebaseFirestore
): UserRepository {
//    override fun getBooksFromFirestore() = callbackFlow {
//        val snapshotListener = userRef.orderBy(TITLE).addSnapshotListener { snapshot, e ->
//            val response = if (snapshot != null) {
//                val books = snapshot.toObjects(Book::class.java)
//                Success(books)
//            } else {
//                Error(e?.message ?: e.toString())
//            }
//            trySend(response).isSuccess
//        }
//        awaitClose {
//            snapshotListener.remove()
//        }
//    }

    //    override suspend fun addBookToFirestore(title: String, author: String) = flow {
//        try {
//            emit(Loading)
//            val id = userRef.document().id
//            val book = Book(
//                id = id,
//                title = title,
//                author = author
//            )
//            val addition = userRef.document(id).set(book).await()
//            emit(Success(addition))
//        } catch (e: Exception) {
//            emit(Error(e.message ?: e.toString()))
//        }
//    }
//
//    override suspend fun deleteBookFromFirestore(bookId: String) = flow {
//        try {
//            emit(Loading)
//            val deletion = userRef.document(bookId).delete().await()
//            emit(Success(deletion))
//        } catch (e: Exception) {
//            emit(Error(e.message ?: e.toString()))
//        }
//    }
    override suspend fun addUserToFireStore(appUser: AppUser) = flow {

        try {
            emit(Loading)
            val id = userRef.collection(Constants.USER).document().id
            val addition = appUser.id?.let { userRef.collection(Constants.USER).document(it).set(appUser).await()
            }
            emit(Success(addition))
        } catch (e: Exception) {
            emit(Error(e.message ?: e.toString()))
        }
        }

    override suspend fun createWalletToFireStore(wallet: Wallet)= flow {

        try {
            emit(Loading)
            val id = userRef.collection(Constants.WALLET).document().id
            wallet.walletId=id;
            val addition =
                wallet.user_id?.let { userRef.collection(Constants.WALLET).document(id).set(wallet).await() }
            emit(Success(addition))
        } catch (e: Exception) {
            emit(Error(e.message ?: e.toString()))
        }
    }
}
