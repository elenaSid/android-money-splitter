package com.elena.moneysplitter.data.family

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.elena.domain.family.FamilyEntity
import io.reactivex.Flowable

/**
 * @author elena
 *         Date: 12/01/2019
 *         Time: 19:01
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