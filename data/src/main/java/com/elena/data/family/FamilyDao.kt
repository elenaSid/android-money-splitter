package com.elena.data.family

import androidx.room.*

/**
 * @author elena
 */
@Dao
interface FamilyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrReplace(family: FamilyDbEntity)

    @Query("SELECT * FROM family")
    fun getAll(): List<FamilyDbEntity>

    @Query("SELECT * FROM family WHERE id = :id")
    fun get(id: Int): FamilyDbEntity

    @Query("SELECT * FROM family ORDER BY id DESC LIMIT 1")
    fun getLast(): FamilyDbEntity

    @Delete
    fun delete(family: FamilyDbEntity)

    @Query("DELETE FROM family")
    fun deleteAll()
}