package com.ffs.fortunefundadmin.domain.use_case

data class UseCases (
    val getBooks: GetBooks,
    val getProfile: GetProfile,
    val getAllUsers : GetAllProfile,
    val addInvestmentPlan: AddInvestmentPlan,
    val deleteBook: DeleteBook
)