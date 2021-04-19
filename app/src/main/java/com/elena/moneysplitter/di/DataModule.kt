package com.elena.moneysplitter.di

import android.content.Context
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
    fun provideKeyValueStorage(context: Context): KeyValueStorage {
        return PreferencesStorage(context)
    }

    @Provides
    @PerApplication
    fun provideAppDatabase(context: Context): RoomDb {
        return Room.databaseBuilder(context, RoomDb::class.java, "money-splitter-db.db")
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