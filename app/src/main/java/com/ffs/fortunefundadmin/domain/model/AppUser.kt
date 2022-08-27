package com.ffs.fortunefundadmin.domain.model

data class AppUser (
    var id: String? = null,
    var name: String? = null,
    var mobile: String? = null,
    var email: String? = null,
    var userType: String? = null,
    var imageUrl: String? = null,
)