package com.elena.moneysplitter.di

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.elena.domain.common.KeyValueStorage
import com.elena.domain.family.FamilyRepository
import com.elena.moneysplitter.data.PreferencesStorage
import com.elena.moneysplitter.data.common.RealmManager
import com.elena.moneysplitter.data.db.RoomDb
import com.elena.moneysplitter.data.family.FamilyDbMapper
import com.elena.moneysplitter.data.family.FamilyRepositoryImpl
import dagger.Module
import dagger.Provides
import io.realm.Realm

/**
 * @author elena
 *         Date: 11.06.2018
 *         Time: 19:34
 */
@Module
class DataModule {

    @Provides
    @PerApplication
    fun provideRealmDatabase(realmManager: RealmManager): Realm {
        return realmManager.getDatabase()
    }

    @Provides
    @PerApplication
    fun provideRealmManager(context: Context): RealmManager {
        return RealmManager(context)
    }

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
}