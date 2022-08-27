package com.ffs.fortunefundadmin.domain.use_case

import com.ffs.fortunefundadmin.domain.repository.InsurancePlanRepository
import com.google.firebase.auth.FirebaseAuth

class GetProfile (
    private val repo: InsurancePlanRepository
) {
    operator fun invoke() = FirebaseAuth.getInstance().currentUser?.let {
        repo.getProfileFromFirestore(
            it.uid)
    }

}