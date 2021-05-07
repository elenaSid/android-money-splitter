package com.elena.data.item

import com.elena.data.common.TwoWayDataMapper
import com.elena.domain.item.ItemEntity

/**
 * @author elena
 */
class ItemDbMapper : TwoWayDataMapper<ItemDbEntity, ItemEntity> {

    override fun map2(data: ItemEntity): ItemDbEntity {
        return ItemDbEntity(
                data.id,
                data.name,
                data.price,
                data.payedByUserIds.toList(),
                data.usedByUserIds.toList()
        )
    }

    override fun map(data: ItemDbEntity): ItemEntity {
        return ItemEntity(
                data.id,
                data.item,
                data.price,
                data.payedByUserIds.toSet(),
                data.usedByUserIds.toSet()
        )
    }
}