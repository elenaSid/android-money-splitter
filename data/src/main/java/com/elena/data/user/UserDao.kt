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

    @Query("SELECT * FROM user WHERE family_id = :familyId")
    fun getUsersWithFamily(familyId: Int): List<UserDbEntity>

    @Query("SELECT * FROM user WHERE family_id is NULL")
    fun getUsersWithoutFamily(): List<UserDbEntity>

    @Query("SELECT * FROM user WHERE id = :userId")
    fun get(userId: Int): UserDbEntity

    @Delete
    fun delete(user: UserDbEntity)

    @Query("DELETE FROM user")
    fun deleteAll()
}