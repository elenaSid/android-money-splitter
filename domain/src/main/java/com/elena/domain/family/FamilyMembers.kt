package com.elena.domain.family

import com.elena.domain.user.UserEntity

/**
 * @author elena
 */
data class FamilyMembers(
        private val family: FamilyEntity,
        private val users: List<UserEntity>
)