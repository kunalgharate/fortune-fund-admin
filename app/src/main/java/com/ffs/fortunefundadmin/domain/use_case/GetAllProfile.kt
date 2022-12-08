package com.ffs.fortunefundadmin.domain.use_case

import com.ffs.fortunefundadmin.domain.repository.InsurancePlanRepository
import com.google.firebase.auth.FirebaseAuth

class GetAllProfile (
    private val repo: InsurancePlanRepository
) {
    operator fun invoke() = repo.getAllUsers();

}