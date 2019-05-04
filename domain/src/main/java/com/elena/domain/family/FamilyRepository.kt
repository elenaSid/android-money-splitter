package com.elena.domain.family

import io.reactivex.Flowable

interface FamilyRepository {
    fun getAll(): List<FamilyEntity>

    fun save(family: FamilyEntity)
}