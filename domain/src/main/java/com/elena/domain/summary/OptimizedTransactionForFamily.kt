package com.elena.domain.summary

data class OptimizedTransactionForFamily(
        val debtorFamilyId: String?, //could be null in case it is a user without family (single person in a family)
        val debtorFamilyName: String, //in case debtorFamilyId is null we will store user.name here

        val creditorFamilyId: String?, //could be null in case it is a user without family (single person in a family)
        val creditorFamilyName: String, //in case creditorFamilyId is null we will store user.name here

        val debt: Double
)