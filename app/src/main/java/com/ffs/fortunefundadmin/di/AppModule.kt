package com.ffs.fortunefundadmin.di

import com.ffs.fortunefundadmin.domain.repository.InsurancePlanRepository
import com.ffs.fortunefundadmin.domain.repository.UserRepository
import com.ffs.fortunefundadmin.domain.use_case.*
import com.ffs.fortunefundadmin.repository.InvestmentPlanRepositoryImpl
import com.ffs.fortunefundadmin.repository.UserRepositoryImpl
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

//@Module(includes = [UserRepositoryImpl::class,BooksRepositoryImpl::class])
@Module()
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideFirebaseFirestore() = Firebase.firestore
//
//    @Provides
//    fun provideBooksRef(db: FirebaseFirestore) = db

    @Provides
    fun provideBooksRepository(
        //booksRef: FirebaseFirestore
    ): InsurancePlanRepository = InvestmentPlanRepositoryImpl(provideFirebaseFirestore())

    @Provides
    fun provideUseCases(repo: InsurancePlanRepository) = UseCases(
        getBooks = GetBooks(repo),
        getProfile = GetProfile(repo),
      //  addBook = AddBook(repo),
        deleteBook = DeleteBook(repo)
    )



    @Provides
    fun provideUserRepository(
       // userRef: CollectionReference
    ): UserRepository = UserRepositoryImpl(provideFirebaseFirestore())

    @Provides
    fun provideUserUseCases(repo: UserRepository) = UserUseCases(
        addUser = AddUser(repo),
       addWallet =  CreateWallet(repo)

    )
}

