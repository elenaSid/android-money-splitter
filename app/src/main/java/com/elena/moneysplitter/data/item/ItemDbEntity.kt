package com.elena.moneysplitter.data.item

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * @author elena
 *         Date: 12/01/2019
 *         Time: 18:10
 */
@Entity(tableName = "item")
class ItemDbEntity(
        @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Int,
        @ColumnInfo(name = "item_name") var item: String,
        @ColumnInfo(name = "price") var price: Double,

        @ColumnInfo(name = "payed_by_users") var payedByUserIds: List<Int>,
        @ColumnInfo(name = "used_by_users") var usedByUserIds: List<Int>
)