package com.elena.domain.user.interaction

import com.elena.domain.common.CoroutineUseCase
import com.elena.domain.user.UserEntity
import com.elena.domain.user.UserRepository

/**
 * @author elena
 */
class GetAllUsersUseCase(private val userRepository: UserRepository) : CoroutineUseCase<Unit, List<UserEntity>>() {

    override suspend fun runUseCase(param: Unit): List<UserEntity> {
        return userRepository.getAll()
    }
}