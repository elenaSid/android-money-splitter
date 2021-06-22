package com.elena.domain.family.interaction

import com.elena.domain.common.CoroutineUseCase
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

) : CoroutineUseCase<Int, FamilyMembers>() {

    override suspend fun runUseCase(param: Int): FamilyMembers {
        return FamilyMembers(familyRepository.get(param), userRepository.getUsersWithFamily(param))
    }
}