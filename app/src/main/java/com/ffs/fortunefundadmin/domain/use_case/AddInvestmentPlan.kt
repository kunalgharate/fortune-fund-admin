package com.ffs.fortunefundadmin.domain.use_case

import com.ffs.fortunefundadmin.domain.model.InvestmentPlan
import com.ffs.fortunefundadmin.domain.repository.InsurancePlanRepository

class AddInvestmentPlan(
    private val repo: InsurancePlanRepository
)
{
    suspend operator fun invoke(investment:InvestmentPlan
    ) = repo.addInvestmentPlan(investmentPlan = investment )
}