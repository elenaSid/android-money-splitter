package com.elena.domain.family

interface FamilyRepository {
    fun getAll(): List<FamilyEntity>

    fun save(family: FamilyEntity)

    fun getLast(): FamilyEntity

    fun get(id: Int): FamilyEntity
}