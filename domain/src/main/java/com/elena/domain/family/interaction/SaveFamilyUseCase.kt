package com.elena.domain.family.interaction

import com.elena.domain.common.UseCase
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
) : UseCase<SaveFamilyUseCase.Param, Unit>() {

    override fun runUseCase(param: Param) {
        if (param.familyId == null) {
            familyRepository.save(FamilyEntity(name = param.familyName))
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