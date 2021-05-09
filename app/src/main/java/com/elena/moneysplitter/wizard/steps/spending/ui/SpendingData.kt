package com.elena.moneysplitter.wizard.steps.spending.ui

/**
 * @author elena
 */
data class SpendingData(
        val itemId: Int,
        val name: String,
        val price: Double,
        val payers: List<String>,
        val consumers: List<String>
)