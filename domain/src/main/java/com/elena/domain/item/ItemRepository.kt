package com.elena.domain.item

interface ItemRepository {
    fun getAll(): List<ItemEntity>
}