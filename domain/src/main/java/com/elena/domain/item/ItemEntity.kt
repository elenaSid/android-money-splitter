package com.elena.domain.item

data class ItemEntity(
        val id: String,
        val name: String,
        val price: Double,

        val payedByUserIds: Set<String>,
        val usedByUserIds: Set<String>
)