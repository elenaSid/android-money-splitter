package com.elena.domain.summary

/**
 * @author elena
 */
data class SummaryForUser(
        val userId: Int,
        val userName: String,

        val paid: Double,
        val spent: Double
) {
    fun getDifference() = paid - spent
}