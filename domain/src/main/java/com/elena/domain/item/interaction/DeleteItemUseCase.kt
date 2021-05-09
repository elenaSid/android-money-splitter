package com.elena.domain.item.interaction

import com.elena.domain.common.UseCase
import com.elena.domain.item.ItemEntity
import com.elena.domain.item.ItemRepository

/**
 * @author elena
 */
class DeleteItemUseCase(private val itemRepository: ItemRepository) : UseCase<ItemEntity, Unit>() {
    override fun runUseCase(param: ItemEntity) {
        itemRepository.delete(param)
    }
}