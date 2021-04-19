package com.elena.domain.item

data class ItemEntity(
        val id: Int,
        val name: String,
        val price: Double,

        val payedByUserIds: Set<Int>,
        val usedByUserIds: Set<Int>
)