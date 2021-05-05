package com.elena.domain.family.interaction

import com.elena.domain.common.UseCase
import com.elena.domain.family.FamilyEntity
import com.elena.domain.family.FamilyRepository

/**
 * Сценарий удаления семьи
 *
 * @author elena
 */
class DeleteFamilyUseCase(private val familyRepository: FamilyRepository) : UseCase<FamilyEntity, Unit>() {
    override fun runUseCase(param: FamilyEntity) {
        familyRepository.delete(param)
    }
}