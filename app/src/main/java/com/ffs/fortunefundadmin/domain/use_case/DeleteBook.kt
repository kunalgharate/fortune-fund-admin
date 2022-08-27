package com.ffs.fortunefundadmin.domain.use_case

import com.ffs.fortunefundadmin.domain.repository.InsurancePlanRepository

class DeleteBook(
    private val repo: InsurancePlanRepository
) {
    suspend operator fun invoke(bookId: String) = repo.deleteBookFromFirestore(bookId)
}