package com.elena.domain.family.interaction

import com.elena.domain.common.UseCase
import com.elena.domain.family.FamilyEntity
import com.elena.domain.family.FamilyRepository

/**
 * @author elena
 */
class GetFamiliesUseCase(private val familyRepository: FamilyRepository) : UseCase<Unit, List<FamilyEntity>>() {

    override fun runUseCase(param: Unit): List<FamilyEntity> {
        return familyRepository.getAll()
    }
}