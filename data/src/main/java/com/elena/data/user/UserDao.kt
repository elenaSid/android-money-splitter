package com.elena.data.user

import androidx.room.*

/**
 * @author elena
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