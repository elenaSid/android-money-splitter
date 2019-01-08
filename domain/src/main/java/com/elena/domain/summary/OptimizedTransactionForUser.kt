package com.elena.domain.summary

data class OptimizedTransactionForUser(
        val debtorUserId: String,
        val debtorUserName: String,

        val creditorUserId: String,
        val creditorUserName: String,

        val debt: Double
)