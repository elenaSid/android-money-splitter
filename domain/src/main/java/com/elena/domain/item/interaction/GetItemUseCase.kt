package com.elena.domain.item.interaction

import com.elena.domain.common.UseCase
import com.elena.domain.item.ItemEntity
import com.elena.domain.item.ItemRepository

/**
 * @author elena
 */
class GetItemUseCase(private val itemRepository: ItemRepository) : UseCase<Int, ItemEntity>() {
    override fun runUseCase(param: Int): ItemEntity {
        return itemRepository.get(param)
    }
}