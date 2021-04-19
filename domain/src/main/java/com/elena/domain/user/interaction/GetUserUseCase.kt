package com.elena.domain.user.interaction

import com.elena.domain.common.UseCase
import com.elena.domain.user.UserEntity
import com.elena.domain.user.UserRepository

/**
 * @author elena
 *         Date: 2019-05-05
 *         Time: 12:51
 */
class GetUserUseCase(private val userRepository: UserRepository) : UseCase<Int, UserEntity>() {

    override fun runUseCase(param: Int): UserEntity {
        return userRepository.get(param)
    }
}