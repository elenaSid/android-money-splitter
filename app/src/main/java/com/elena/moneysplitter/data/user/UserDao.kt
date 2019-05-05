package com.elena.moneysplitter.data.user

import android.arch.persistence.room.*

/**
 * @author elena
 *         Date: 12/01/2019
 *         Time: 19:09
 */
@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrReplace(user: UserDbEntity)

    @Query("SELECT * FROM user")
    fun getAll(): List<UserDbEntity>

    @Query("SELECT * FROM user WHERE id = :userId")
    fun get(userId: Int): UserDbEntity

    @Delete
    fun delete(user: UserDbEntity)
}