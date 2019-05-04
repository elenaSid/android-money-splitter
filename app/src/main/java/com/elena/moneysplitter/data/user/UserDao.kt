package com.elena.moneysplitter.data.user

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

/**
 * @author elena
 *         Date: 12/01/2019
 *         Time: 19:09
 */
@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertOrReplace(user: UserDbEntity)

    @Query("SELECT * FROM user")
    fun getAll(): List<UserDbEntity>
}