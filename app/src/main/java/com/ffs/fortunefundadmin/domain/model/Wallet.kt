package com.ffs.fortunefundadmin.domain.model

data class Wallet (
    var walletId: String? = null,
    var balance:Int = 0,
    var description: String? = null,
    var upi_id: String? = null,
    var user_id: String? = null,
)