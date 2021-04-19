package com.elena.moneysplitter.data.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import com.elena.moneysplitter.data.family.FamilyDbEntity

/**
 * @author elena
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