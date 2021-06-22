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

    override suspend fun getAll(): List<ItemEntity> {
        return itemDao.getAll().map { mapper.map(it) }
    }

    override suspend fun save(item: ItemEntity) {
        itemDao.insertOrReplace(mapper.map2(item))
    }

    override suspend fun get(id: Int): ItemEntity {
        return mapper.map(itemDao.get(id))
    }

    override suspend fun delete(item: ItemEntity) {
        itemDao.delete(mapper.map2(item))
    }

    override suspend fun deleteAll() {
        itemDao.deleteAll()
    }
}