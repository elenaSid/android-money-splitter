package com.elena.domain.summary

data class SummaryForUser(
        val userId: Int,
        val userName: String,

        val paid: Double,
        val spent: Double
) {
    fun getDifference() = paid - spent
}