package com.elena.moneysplitter.di

import android.content.Context
import com.elena.domain.common.KeyValueStorage
import com.elena.moneysplitter.data.PreferencesStorage
import com.elena.moneysplitter.data.common.RealmManager
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
}