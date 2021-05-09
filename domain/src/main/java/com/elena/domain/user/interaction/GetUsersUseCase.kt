package com.elena.domain.user.interaction

import com.elena.domain.common.UseCase
import com.elena.domain.user.UserEntity
import com.elena.domain.user.UserRepository

/**
 * @author elena
 */
class GetUsersUseCase(private val userRepository: UserRepository): UseCase<Set<Int>, List<UserEntity>>() {
    override fun runUseCase(param: Set<Int>): List<UserEntity> {
        return param.map { userRepository.get(it) }
    }
}