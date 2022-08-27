package com.ffs.fortunefundadmin.domain.use_case

import com.ffs.fortunefundadmin.domain.model.Wallet
import com.ffs.fortunefundadmin.domain.repository.UserRepository

class CreateWallet(
    private val repo: UserRepository
) {
    suspend operator fun invoke(
        wallet: Wallet
    ) = repo.createWalletToFireStore(wallet)
}