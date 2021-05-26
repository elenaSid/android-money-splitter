package com.elena.domain.user.interaction

import com.elena.domain.common.UseCase
import com.elena.domain.family.FamilyRepository
import com.elena.domain.item.ItemRepository
import com.elena.domain.user.UserRepository

/**
 * @author elena
 */
class RemoveAllDataUseCase(
        private val familyRepository: FamilyRepository,
        private val itemRepository: ItemRepository,
        private val userRepository: UserRepository
) : UseCase<Unit, Unit>() {

    override fun runUseCase(param: Unit) {
        itemRepository.deleteAll()
        familyRepository.deleteAll()
        userRepository.deleteAll()
    }
}