package com.elena.moneysplitter.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.elena.moneysplitter.data.family.FamilyDao
import com.elena.moneysplitter.data.family.FamilyDbEntity
import com.elena.moneysplitter.data.item.ItemDao
import com.elena.moneysplitter.data.item.ItemDbEntity
import com.elena.moneysplitter.data.user.UserDao
import com.elena.moneysplitter.data.user.UserDbEntity

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