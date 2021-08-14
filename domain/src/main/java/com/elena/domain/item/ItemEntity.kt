package com.elena.domain.item

/**
 * @author elena
 */
data class ItemEntity(
        val id: Int = 0,
        val name: String,
        val price: Double,

        val payedByUserIds: Set<Int>,
        val usedByUserIds: Set<Int>
)