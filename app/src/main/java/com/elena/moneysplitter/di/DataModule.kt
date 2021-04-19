package com.elena.moneysplitter.di

import android.app.Application
import androidx.room.Room
import com.elena.domain.common.KeyValueStorage
import com.elena.domain.family.FamilyRepository
import com.elena.domain.user.UserRepository
import com.elena.moneysplitter.data.PreferencesStorage
import com.elena.moneysplitter.data.db.RoomDb
import com.elena.moneysplitter.data.family.FamilyDbMapper
import com.elena.moneysplitter.data.family.FamilyRepositoryImpl
import com.elena.moneysplitter.data.user.UserDbMapper
import com.elena.moneysplitter.data.user.UserRepositoryImpl
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
}