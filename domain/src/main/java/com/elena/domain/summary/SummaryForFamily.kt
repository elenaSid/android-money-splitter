package com.elena.domain.summary

data class SummaryForFamily(
        val familyId: Int?, //could be null in case it is a user without family (single person in a family)
        val familyName: String, // in case familyId is null we will store user.name here

        val paid: Double,
        val spent: Double,
        val summaryForUsers: List<SummaryForUser>
) {
    fun getDifference() = paid - spent
}