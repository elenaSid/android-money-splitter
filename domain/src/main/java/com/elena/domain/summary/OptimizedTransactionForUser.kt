package com.elena.domain.summary

/**
 * @author elena
 */
data class OptimizedTransactionForUser(
        val debtorUserId: Int,
        val debtorUserName: String,

        val creditorUserId: Int,
        val creditorUserName: String,

        val debt: Double
)