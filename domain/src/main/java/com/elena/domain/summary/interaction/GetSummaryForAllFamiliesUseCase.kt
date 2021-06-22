package com.elena.domain.summary.interaction

import com.elena.domain.common.CoroutineUseCase
import com.elena.domain.family.FamilyRepository
import com.elena.domain.summary.SummaryForFamily
import com.elena.domain.user.UserRepository

class GetSummaryForAllFamiliesUseCase(
        private val getSummaryForAllUsersUseCase: GetSummaryForAllUsersUseCase,
        private val userRepository: UserRepository,
        private val familyRepository: FamilyRepository
) : CoroutineUseCase<Unit, Set<SummaryForFamily>>() {

    override suspend fun runUseCase(param: Unit): Set<SummaryForFamily> {
        val summaryForUsers = getSummaryForAllUsersUseCase.execute(Unit)
        if (summaryForUsers.isEmpty()) return emptySet()

        val allUsers = userRepository.getAll()

        val usersWithFamilyById = allUsers.filter { it.familyId != null }.associateBy { it.id }
        val usersWithoutFamilyById = allUsers.filter { it.familyId == null }.associateBy { it.id }

        val summaryForUsersWithFamily = summaryForUsers.filter { it.userId in usersWithFamilyById.keys }
        val summaryForUsersWithoutFamily = summaryForUsers.filter { it.userId in usersWithoutFamilyById.keys }

        if (summaryForUsersWithFamily.size + summaryForUsersWithoutFamily.size != summaryForUsers.size)
            throw Exception("We have summary for non-existing users")

        val result = summaryForUsersWithoutFamily.mapTo(HashSet()) { summaryForUserWithoutFamily ->
            SummaryForFamily(
                    familyId = null,
                    familyName = summaryForUserWithoutFamily.userName,
                    paid = summaryForUserWithoutFamily.paid,
                    spent = summaryForUserWithoutFamily.spent,
                    summaryForUsers = listOf(summaryForUserWithoutFamily)
            )
        }

        if (summaryForUsersWithFamily.isEmpty())
            return result

        val allFamilies = familyRepository.getAll()
        if (allFamilies.isEmpty()) throw Exception("We have no families, but users have links to families")

        val paidByFamilyId = HashMap<Int, Double>().withDefault { 0.0 }
        val spentByFamilyId = HashMap<Int, Double>().withDefault { 0.0 }

        summaryForUsersWithFamily.forEach { summaryForUser ->
            val familyId = usersWithFamilyById.getValue(summaryForUser.userId).familyId!!
            paidByFamilyId[familyId] = paidByFamilyId.getValue(familyId) + summaryForUser.paid
            spentByFamilyId[familyId] = spentByFamilyId.getValue(familyId) + summaryForUser.spent
        }


        allFamilies.forEach { family ->
            val id = family.id
            val usersInFamily = usersWithFamilyById.filterValues { it.familyId == id }
            result.add(SummaryForFamily(
                    familyId = id,
                    familyName = family.name,
                    paid = paidByFamilyId.remove(id) ?: 0.0,
                    spent = spentByFamilyId.remove(id) ?: 0.0,
                    summaryForUsers = summaryForUsersWithFamily.filter { it.userId in usersInFamily.keys }
            ))
        }

        if (paidByFamilyId.isNotEmpty())
            throw Exception("Inconsistency in items. We've found items which are linked with non-existing families: $paidByFamilyId")

        if (spentByFamilyId.isNotEmpty())
            throw Exception("Inconsistency in items. We've found items which are linked with non-existing families: $spentByFamilyId")

        return result
    }
}

