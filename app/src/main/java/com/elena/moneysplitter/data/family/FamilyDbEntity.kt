package com.elena.moneysplitter.data.family

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author elena
 */
@Entity(tableName = "family")
data class FamilyDbEntity (
        @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Int,
        @ColumnInfo(name = "family_name") var family: String
)