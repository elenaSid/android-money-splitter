package com.elena.domain.family.interaction

import com.elena.domain.common.UseCase
import com.elena.domain.family.FamilyMembers
import com.elena.domain.family.FamilyRepository
import com.elena.domain.user.UserRepository

/**
 * Сценарий получения семьи со всеми ее участниками
 *
 * @author elena
 */
class GetFamilyWithMembersUseCase(
        private val familyRepository: FamilyRepository,
        private val userRepository: UserRepository

) : UseCase<Int, FamilyMembers>() {

    override fun runUseCase(param: Int): FamilyMembers {
        return FamilyMembers(familyRepository.get(param), userRepository.getUsersWithFamily(param))
    }
}