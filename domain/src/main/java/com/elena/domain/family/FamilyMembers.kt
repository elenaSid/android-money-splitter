package com.elena.domain.family

import com.elena.domain.user.UserEntity

/**
 * @author elena
 */
data class FamilyMembers(
        val family: FamilyEntity,
        val users: List<UserEntity>
)