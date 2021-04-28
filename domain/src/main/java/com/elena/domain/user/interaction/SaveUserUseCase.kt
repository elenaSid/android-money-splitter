package com.elena.domain.user.interaction

import com.elena.domain.common.UseCase
import com.elena.domain.family.FamilyEntity
import com.elena.domain.user.UserEntity
import com.elena.domain.user.UserRepository

/**
 * Сценарий создания нового пользователя
 *
 * @author elena
 */
class SaveUserUseCase(private val userRepository: UserRepository) : UseCase<String, Unit>() {

    override fun runUseCase(param: String) {
        userRepository.save(UserEntity(name = param, familyId = null))
        return
    }
}