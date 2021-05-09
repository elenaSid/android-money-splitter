package com.elena.moneysplitter.di

import android.app.Application
import androidx.room.Room
import com.elena.domain.common.KeyValueStorage
import com.elena.domain.family.FamilyRepository
import com.elena.domain.user.UserRepository
import com.elena.data.PreferencesStorage
import com.elena.data.db.RoomDb
import com.elena.data.family.FamilyDbMapper
import com.elena.data.family.FamilyRepositoryImpl
import com.elena.data.item.ItemDbMapper
import com.elena.data.item.ItemRepositoryImpl
import com.elena.data.user.UserDbMapper
import com.elena.data.user.UserRepositoryImpl
import com.elena.domain.item.ItemRepository
import dagger.Module
import dagger.Provides

/**
 * @author elena
 */
@Module
class DataModule {

    @Provides
    @PerApplication
    fun provideKeyValueStorage(applicationContext: Application): KeyValueStorage {
        return PreferencesStorage(applicationContext)
    }

    @Provides
    @PerApplication
    fun provideAppDatabase(applicationContext: Application): RoomDb {
        return Room.databaseBuilder(applicationContext, RoomDb::class.java, "money-splitter-db.db")
                .allowMainThreadQueries()
                //.addMigrations()
                .build()
    }

    @Provides
    @PerApplication
    fun provideFamilyRepository(db: RoomDb): FamilyRepository {
        return FamilyRepositoryImpl(db.familyDao(), FamilyDbMapper())
    }

    @Provides
    @PerApplication
    fun provideUserRepository(db: RoomDb): UserRepository {
        return UserRepositoryImpl(db.userDao(), UserDbMapper())
    }

    @Provides
    @PerApplication
    fun provideItemRepository(db: RoomDb): ItemRepository {
        return ItemRepositoryImpl(db.itemDao(), ItemDbMapper())
    }
}