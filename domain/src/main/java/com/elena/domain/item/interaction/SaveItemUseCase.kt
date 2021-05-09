package com.elena.domain.item.interaction

import com.elena.domain.common.UseCase
import com.elena.domain.item.ItemEntity
import com.elena.domain.item.ItemRepository
import com.elena.domain.user.UserEntity

/**
 * @author elena
 */
class SaveItemUseCase(
        private val itemRepository: ItemRepository
) : UseCase<SaveItemUseCase.Param, Unit>() {

    override fun runUseCase(param: Param) {
        val payers = param.payers.map { it.id }.toSet()
        val consumers = param.consumers.map { it.id }.toSet()

        if (param.itemId == null) {
            itemRepository.save(
                    ItemEntity(
                            name = param.itemName,
                            price = param.price,
                            payedByUserIds = payers,
                            usedByUserIds = consumers
                    )
            )
        } else {
            itemRepository.save(
                    ItemEntity(
                            id = param.itemId,
                            name = param.itemName,
                            price = param.price,
                            payedByUserIds = payers,
                            usedByUserIds = consumers
                    )
            )
        }
        return
    }

    class Param(val itemId: Int?,
                val itemName: String,
                val price: Double,
                val payers: List<UserEntity>,
                val consumers: List<UserEntity>)
}

