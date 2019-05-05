package com.elena.domain.user

data class UserEntity(
        val id: Int = 0,
        val name: String,

        val familyId: Int?, //null means a single person in the family. FamilyEntity.id
        val familyName: String? //null means a single person in the family. FamilyEntity.name
)