package com.elena.moneysplitter.data.user

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.ForeignKey.CASCADE
import android.arch.persistence.room.PrimaryKey
import com.elena.moneysplitter.data.family.FamilyDbEntity

/**
 * @author elena
 *         Date: 12/01/2019
 *         Time: 17:54
 */
@Entity(tableName = "user",
        foreignKeys = [ForeignKey(entity = FamilyDbEntity::class,
                parentColumns = ["id"],
                childColumns = ["family_id"],
                onDelete = CASCADE)
        ])
data class UserDbEntity(
        @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Int,
        @ColumnInfo(name = "user_name") var name: String,
        @ColumnInfo(name = "family_id") var familyId: Int?
)