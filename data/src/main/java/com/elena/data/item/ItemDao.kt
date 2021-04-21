package com.elena.data.item

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


/**
 * @author elena
 */
@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertOrReplace(user: ItemDbEntity)

    @Query("SELECT * FROM item")
    fun getAll(): List<ItemDbEntity>
}