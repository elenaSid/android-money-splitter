package com.elena.domain.user

data class UserEntity(
        val id: String,
        val name: String,

        val familyId: String?, //null means a single person in the family. FamilyEntity.id
        val familyName: String? //null means a single person in the family. FamilyEntity.name
)