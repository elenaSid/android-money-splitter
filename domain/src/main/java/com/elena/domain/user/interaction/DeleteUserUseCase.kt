package com.elena.domain.user.interaction

import com.elena.domain.common.UseCase
import com.elena.domain.user.UserEntity
import com.elena.domain.user.UserRepository

/**
 * @author elena
 *         Date: 2019-05-05
 *         Time: 18:58
 */
class DeleteUserUseCase(private val userRepository: UserRepository) : UseCase<UserEntity, Unit>() {
    override fun runUseCase(param: UserEntity) {
        userRepository.delete(param)
    }
}