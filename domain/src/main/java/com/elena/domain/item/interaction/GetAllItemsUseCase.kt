package com.elena.domain.item.interaction

import com.elena.domain.common.UseCase
import com.elena.domain.item.ItemEntity
import com.elena.domain.item.ItemRepository

class GetAllItemsUseCase(private val itemRepository: ItemRepository) : UseCase<Unit, List<ItemEntity>>() {
    override fun runUseCase(param: Unit): List<ItemEntity> {
        return itemRepository.getAll()
    }
}