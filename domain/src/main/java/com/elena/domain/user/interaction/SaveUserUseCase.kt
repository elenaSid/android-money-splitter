package com.elena.domain.user.interaction

import com.elena.domain.common.CoroutineUseCase
import com.elena.domain.user.UserEntity
import com.elena.domain.user.UserRepository

/**
 * Сценарий создания нового пользователя
 *
 * @author elena
 */
class SaveUserUseCase(private val userRepository: UserRepository) : CoroutineUseCase<String, Unit>() {

    override suspend fun runUseCase(param: String) {
        userRepository.save(UserEntity(name = param, familyId = null))
        return
    }
}