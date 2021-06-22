package com.elena.domain.item.interaction

import com.elena.domain.common.CoroutineUseCase
import com.elena.domain.item.ItemEntity
import com.elena.domain.item.ItemRepository

/**
 * @author elena
 */
class DeleteItemUseCase(private val itemRepository: ItemRepository) : CoroutineUseCase<ItemEntity, Unit>() {
    override suspend fun runUseCase(param: ItemEntity) {
        itemRepository.delete(param)
    }
}