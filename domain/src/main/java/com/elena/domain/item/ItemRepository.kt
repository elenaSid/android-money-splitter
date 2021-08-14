package com.elena.domain.item

/**
 * @author elena
 */
interface ItemRepository {
    suspend fun getAll(): List<ItemEntity>

    suspend fun save(item: ItemEntity)

    suspend fun get(id: Int): ItemEntity

    suspend fun delete(item: ItemEntity)

    suspend fun deleteAll()
}