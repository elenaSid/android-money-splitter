package com.elena.domain.user.interaction

import com.elena.domain.common.UseCase
import com.elena.domain.user.UserEntity
import com.elena.domain.user.UserRepository

/**
 * @author elena
 *         Date: 08/01/2019
 *         Time: 12:49
 */
class GetAllUsersUseCase(private val userRepository: UserRepository) : UseCase<Unit, List<UserEntity>>() {

    override fun runUseCase(param: Unit?): List<UserEntity>? {
        return userRepository.getAll()
    }
}