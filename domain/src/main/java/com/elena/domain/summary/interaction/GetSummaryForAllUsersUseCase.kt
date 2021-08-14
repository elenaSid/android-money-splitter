package com.elena.domain.summary.interaction

import com.elena.domain.common.CoroutineUseCase
import com.elena.domain.item.ItemRepository
import com.elena.domain.summary.SummaryForUser
import com.elena.domain.user.UserRepository

/**
 * @author elena
 */
class GetSummaryForAllUsersUseCase(
        private val userRepository: UserRepository,
        private val itemRepository: ItemRepository
) : CoroutineUseCase<Unit, Set<SummaryForUser>>() {

    override suspend fun runUseCase(param: Unit): Set<SummaryForUser> {
        val allItems = itemRepository.getAll()
        if (allItems.isEmpty()) return emptySet()

        val allUsers = userRepository.getAll()
        if (allUsers.isEmpty()) throw Exception("We have items, but have no users")

        val paidByUserId = HashMap<Int, Double>().withDefault { 0.0 }
        val spentByUserId = HashMap<Int, Double>().withDefault { 0.0 }

        allItems.forEach { item ->
            val payerUsersCount = item.payedByUserIds.size
            val paidByEachUser = item.price / payerUsersCount

            item.payedByUserIds.forEach { userId ->
                paidByUserId[userId] = paidByUserId.getValue(userId) + paidByEachUser
            }

            val spenderUsersCount = item.usedByUserIds.size
            val spentByEachUser = item.price / spenderUsersCount

            item.usedByUserIds.forEach { userId ->
                spentByUserId[userId] = spentByUserId.getValue(userId) + spentByEachUser
            }
        }

        val result = allUsers.mapTo(HashSet()) { user ->
            val id = user.id
            SummaryForUser(
                    userId = id,
                    userName = user.name,
                    paid = paidByUserId.remove(id) ?: 0.0,
                    spent = spentByUserId.remove(id) ?: 0.0
            )
        }

        if (paidByUserId.isNotEmpty())
            throw Exception("Inconsistency in items. We've found items which are linked with non-existing users: $paidByUserId")

        if (spentByUserId.isNotEmpty())
            throw Exception("Inconsistency in items. We've found items which are linked with non-existing users: $spentByUserId")

        return result
    }
}