package com.elena.domain.item.interaction

import com.elena.domain.common.CoroutineUseCase
import com.elena.domain.item.ItemEntity
import com.elena.domain.item.ItemRepository

class GetAllItemsUseCase(private val itemRepository: ItemRepository) : CoroutineUseCase<Unit, List<ItemEntity>>() {
    override suspend fun runUseCase(param: Unit): List<ItemEntity> {
        return itemRepository.getAll()
    }
}