package com.ffs.fortunefundadmin.domain.use_case

import com.ffs.fortunefundadmin.domain.model.AppUser
import com.ffs.fortunefundadmin.domain.repository.UserRepository

class AddUser(
    private val repo: UserRepository
) {
    suspend operator fun invoke(
        appUser: AppUser
    ) = repo.addUserToFireStore(appUser)
}