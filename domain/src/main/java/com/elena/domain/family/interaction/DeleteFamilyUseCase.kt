package com.elena.domain.family.interaction

import com.elena.domain.common.CoroutineUseCase
import com.elena.domain.family.FamilyEntity
import com.elena.domain.family.FamilyRepository

/**
 * Сценарий удаления семьи
 *
 * @author elena
 */
class DeleteFamilyUseCase(private val familyRepository: FamilyRepository) : CoroutineUseCase<FamilyEntity, Unit>() {
    override suspend fun runUseCase(param: FamilyEntity) {
        familyRepository.delete(param)
    }
}