package com.elena.domain.family.interaction

import com.elena.domain.common.CoroutineUseCase
import com.elena.domain.family.FamilyEntity
import com.elena.domain.family.FamilyRepository
import com.elena.domain.user.UserEntity
import com.elena.domain.user.UserRepository

/**
 * Сценарий сохранения семьи
 */
class SaveFamilyUseCase(
        private val familyRepository: FamilyRepository,
        private val userRepository: UserRepository
) : CoroutineUseCase<SaveFamilyUseCase.Param, Unit>() {

    override suspend fun runUseCase(param: Param) {
        if (param.familyId == null) {
            familyRepository.save(FamilyEntity(name = param.familyName))
        } else {
            familyRepository.save(FamilyEntity(id = param.familyId, name = param.familyName))
        }
        val familyId = param.familyId ?: familyRepository.getLast().id
        for (user in param.users) {
            val savedUser = user.copy(familyId = familyId)
            userRepository.save(savedUser)
        }
        return
    }

    class Param(
            val familyId: Int?,
            val familyName: String,
            val users: List<UserEntity>
    )
}