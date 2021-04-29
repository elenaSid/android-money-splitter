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
class GetAllFamiliesWithMembersUseCase(
        private val familyRepository: FamilyRepository,
        private val userRepository: UserRepository

) : UseCase<Unit, List<FamilyMembers>>() {

    override fun runUseCase(param: Unit): List<FamilyMembers> {
        return familyRepository.getAll().map {
            FamilyMembers(it, userRepository.getUsersWithFamily(it.id))
        }
    }
}