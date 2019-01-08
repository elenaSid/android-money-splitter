package com.elena.domain.family

interface FamilyRepository {
    fun getAll(): List<FamilyEntity>
}