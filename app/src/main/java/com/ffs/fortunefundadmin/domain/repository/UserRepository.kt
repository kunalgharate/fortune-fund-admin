package com.ffs.fortunefundadmin.domain.repository

import kotlinx.coroutines.flow.Flow
import com.ffs.fortunefundadmin.domain.model.Response
import com.ffs.fortunefundadmin.domain.model.AppUser
import com.ffs.fortunefundadmin.domain.model.Wallet

interface UserRepository {
 //   fun getBooksFromFirestore(): Flow<Response<List<Book>>>

    suspend fun addUserToFireStore(appUser: AppUser): Flow<Response<Void?>>

    suspend fun createWalletToFireStore(appUser: Wallet): Flow<Response<Void?>>

  //  suspend fun deleteBookFromFirestore(bookId: String): Flow<Response<Void?>>
}