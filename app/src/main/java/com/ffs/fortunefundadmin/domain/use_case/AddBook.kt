package com.ffs.fortunefundadmin.domain.use_case

import com.ffs.fortunefundadmin.domain.repository.InsurancePlanRepository

class AddBook(
    private val repo: InsurancePlanRepository
)
//{
//    suspend operator fun invoke(
//        title: String,
//        author: String
//    )// = //repo.addBookToFirestore(title, author)
//}