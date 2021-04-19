package com.elena.domain.summary

data class OptimizedTransactionForUser(
        val debtorUserId: Int,
        val debtorUserName: String,

        val creditorUserId: Int,
        val creditorUserName: String,

        val debt: Double
)