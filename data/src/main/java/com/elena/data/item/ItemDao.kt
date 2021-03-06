package com.elena.data.item

import androidx.room.*

/**
 * @author elena
 */
@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrReplace(user: ItemDbEntity)

    @Query("SELECT * FROM item")
    fun getAll(): List<ItemDbEntity>

    @Query("SELECT * FROM item WHERE id = :id")
    fun get(id: Int): ItemDbEntity

    @Delete
    fun delete(item: ItemDbEntity)

    @Query("DELETE FROM item")
    fun deleteAll()
}