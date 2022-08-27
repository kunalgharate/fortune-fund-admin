package com.ffs.fortunefundadmin.domain.use_case

import com.ffs.fortunefundadmin.domain.repository.InsurancePlanRepository

class GetBooks (
    private val repo: InsurancePlanRepository
) {
    operator fun invoke() = repo.getInvestmentPlanFromFirestore()
}