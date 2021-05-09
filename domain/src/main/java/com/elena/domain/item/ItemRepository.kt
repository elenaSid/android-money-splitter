package com.elena.domain.item

interface ItemRepository {
    fun getAll(): List<ItemEntity>

    fun save(item: ItemEntity)

    fun get(id: Int): ItemEntity

    fun delete(item: ItemEntity)
}