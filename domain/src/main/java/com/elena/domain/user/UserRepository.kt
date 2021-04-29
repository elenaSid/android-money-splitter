package com.elena.domain.user

/**
 * @author elena
 */
interface UserRepository {
    fun getAll() : List<UserEntity>

    fun getUsersWithFamily(familyId: Int) : List<UserEntity>

    fun save(user: UserEntity)

    fun get(id: Int): UserEntity

    fun delete(user: UserEntity)
}