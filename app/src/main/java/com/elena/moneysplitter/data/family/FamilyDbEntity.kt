package com.elena.moneysplitter.data.family

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * @author elena
 *         Date: 12/01/2019
 *         Time: 17:59
 */
@Entity(tableName = "family")
data class FamilyDbEntity (
        @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Int,
        @ColumnInfo(name = "family_name") var family: String
)