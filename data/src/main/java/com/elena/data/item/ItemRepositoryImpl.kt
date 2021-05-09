package com.elena.data.item

import com.elena.data.common.TwoWayDataMapper
import com.elena.domain.item.ItemEntity
import com.elena.domain.item.ItemRepository

/**
 * @author elena
 */
class ItemRepositoryImpl(
        private val itemDao: ItemDao,
        private val mapper: TwoWayDataMapper<ItemDbEntity, ItemEntity>
) : ItemRepository {

    override fun getAll(): List<ItemEntity> {
        return itemDao.getAll().map { mapper.map(it) }
    }

    override fun save(item: ItemEntity) {
        itemDao.insertOrReplace(mapper.map2(item))
    }

    override fun get(id: Int): ItemEntity {
        return mapper.map(itemDao.get(id))
    }

    override fun delete(item: ItemEntity) {
        itemDao.delete(mapper.map2(item))
    }
}