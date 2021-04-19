package com.elena.domain.family.interaction

import com.elena.domain.common.UseCase
import com.elena.domain.family.FamilyEntity
import com.elena.domain.family.FamilyRepository

/**
 * @author elena
 *         Date: 12/01/2019
 *         Time: 20:30
 */
class AddFamilyUseCase(private val familyRepository: FamilyRepository) : UseCase<String, FamilyEntity>() {
    override fun runUseCase(param: String): FamilyEntity {
        familyRepository.save(FamilyEntity(name = param))
        return familyRepository.getLast()
    }
}