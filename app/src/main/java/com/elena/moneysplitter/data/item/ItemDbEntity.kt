package com.elena.moneysplitter.data.item

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * @author elena
 */
@Entity(tableName = "item")
class ItemDbEntity(
        @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Int,
        @ColumnInfo(name = "item_name") var item: String,
        @ColumnInfo(name = "price") var price: Double,

        @ColumnInfo(name = "payed_by_users") var payedByUserIds: List<Int>,
        @ColumnInfo(name = "used_by_users") var usedByUserIds: List<Int>
)