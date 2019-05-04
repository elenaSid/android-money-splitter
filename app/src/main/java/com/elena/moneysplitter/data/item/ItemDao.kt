package com.elena.moneysplitter.data.item

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

/**
 * @author elena
 *         Date: 12/01/2019
 *         Time: 19:10
 */
@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertOrReplace(user: ItemDbEntity)

    @Query("SELECT * FROM item")
    fun getAll(): List<ItemDbEntity>
}