package com.elena.domain.family

/**
 * @author elena
 */
interface FamilyRepository {
    suspend fun getAll(): List<FamilyEntity>

    suspend fun save(family: FamilyEntity)

    suspend fun getLast(): FamilyEntity

    suspend fun get(id: Int): FamilyEntity

    suspend fun delete(family: FamilyEntity)

    suspend fun deleteAll()
}