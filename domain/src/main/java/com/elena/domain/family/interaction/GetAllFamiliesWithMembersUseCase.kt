package com.elena.domain.family.interaction

import com.elena.domain.common.CoroutineUseCase
import com.elena.domain.family.FamilyMembers
import com.elena.domain.family.FamilyRepository
import com.elena.domain.user.UserRepository

/**
 * Сценарий получения всех семей со всеми их участниками
 *
 * @author elena
 */
class GetAllFamiliesWithMembersUseCase(
        private val familyRepository: FamilyRepository,
        private val userRepository: UserRepository

) : CoroutineUseCase<Unit, List<FamilyMembers>>() {

    override suspend fun runUseCase(param: Unit): List<FamilyMembers> {
        return familyRepository.getAll().map {
            FamilyMembers(it, userRepository.getUsersWithFamily(it.id))
        }
    }
}