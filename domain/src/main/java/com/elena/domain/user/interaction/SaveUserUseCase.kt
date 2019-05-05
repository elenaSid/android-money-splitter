package com.elena.domain.user.interaction

import com.elena.domain.common.UseCase
import com.elena.domain.family.FamilyEntity
import com.elena.domain.user.UserEntity
import com.elena.domain.user.UserRepository

/**
 * @author elena
 *         Date: 2019-05-05
 *         Time: 12:38
 */
class SaveUserUseCase(private val userRepository: UserRepository) : UseCase<SaveUserUseCase.UserParams, Unit>() {

    override fun runUseCase(param: UserParams) {
        val familyId = if (param.familyEntity != null) param.familyEntity.id else null
        val familyName = if (param.familyEntity != null) param.familyEntity.name else null

        if (param.user == null) {
            userRepository.save(UserEntity(name = param.name, familyId = familyId, familyName = familyName))
            return
        }

        userRepository.save(param.user.copy(name = param.name, familyId = familyId, familyName = familyName))
    }

    public class UserParams(val user: UserEntity?,
                            val name: String,
                            val familyEntity: FamilyEntity?)
}