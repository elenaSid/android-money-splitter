package com.elena.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.elena.data.family.FamilyDao
import com.elena.data.family.FamilyDbEntity
import com.elena.data.item.ItemDao
import com.elena.data.item.ItemDbEntity
import com.elena.data.user.UserDao
import com.elena.data.user.UserDbEntity

/**
 * @author elena
 *         Date: 12/01/2019
 *         Time: 18:57
 */
@Database(entities = [FamilyDbEntity::class, UserDbEntity::class, ItemDbEntity::class], version = 1)
@TypeConverters(AppDbConverters::class)
abstract class RoomDb : RoomDatabase() {
    abstract fun familyDao(): FamilyDao
    abstract fun userDao(): UserDao
    abstract fun itemDao(): ItemDao
}