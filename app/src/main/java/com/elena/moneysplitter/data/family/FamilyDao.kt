package com.elena.moneysplitter.data.family

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

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
}