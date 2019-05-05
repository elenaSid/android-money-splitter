package com.elena.domain.family.interaction

import com.elena.domain.common.UseCase
import com.elena.domain.family.FamilyEntity
import com.elena.domain.family.FamilyRepository

/**
 * @author elena
 *         Date: 2019-05-05
 *         Time: 20:47
 */
class GetFamilyUseCase(private val familyRepository: FamilyRepository) : UseCase<Int, FamilyEntity>() {
    override fun runUseCase(param: Int): FamilyEntity {
        return familyRepository.get(param)
    }
}