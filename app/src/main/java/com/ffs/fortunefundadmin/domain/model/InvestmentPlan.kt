package com.ffs.fortunefundadmin.domain.model

import java.util.*

data class InvestmentPlan (
    var id: String? = null,
    var name: String? = null,
    var imageUrl: String? = null,
    var description: String? = null,
    var startDate: Date? = null,
    var endDate: Date? = null
)