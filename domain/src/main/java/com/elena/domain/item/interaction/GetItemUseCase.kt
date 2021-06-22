package com.elena.domain.item.interaction

import com.elena.domain.common.CoroutineUseCase
import com.elena.domain.item.ItemEntity
import com.elena.domain.item.ItemRepository

/**
 * @author elena
 */
class GetItemUseCase(private val itemRepository: ItemRepository) : CoroutineUseCase<Int, ItemEntity>() {
    override suspend fun runUseCase(param: Int): ItemEntity {
        return itemRepository.get(param)
    }
}