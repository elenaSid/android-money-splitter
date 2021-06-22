package com.elena.domain.user.interaction

import com.elena.domain.common.CoroutineUseCase
import com.elena.domain.user.UserEntity
import com.elena.domain.user.UserRepository

/**
 * @author elena
 */
class DeleteUserUseCase(private val userRepository: UserRepository) : CoroutineUseCase<UserEntity, Unit>() {
    override suspend fun runUseCase(param: UserEntity) {
        userRepository.delete(param)
    }
}