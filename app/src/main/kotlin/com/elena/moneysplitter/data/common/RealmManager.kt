package com.elena.moneysplitter.data.common

import android.content.Context
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * @author elena
 *         Date: 12.06.2018
 *         Time: 14:06
 */
class RealmManager(context: Context) {
    companion object {
        private const val DATABASE_NAME: String = "money_splitter_realm.db"
    }

    private var realm: Realm

    init {
        Realm.init(context)
        val realmConfiguration: RealmConfiguration = RealmConfiguration.Builder()
                .name(DATABASE_NAME)
                .build()
        realm = Realm.getInstance(realmConfiguration)
    }

    /**
     * Return instance of Realm database
     */
    fun getDatabase(): Realm {
        return realm
    }
}