package com.elena.domain.user

/**
 * @author elena
 */
interface UserRepository {
    suspend fun getAll() : List<UserEntity>

    suspend fun getUsersWithFamily(familyId: Int) : List<UserEntity>

    suspend fun getUsersWithoutFamily() : List<UserEntity>

    suspend fun save(user: UserEntity)

    suspend fun get(id: Int): UserEntity

    suspend fun delete(user: UserEntity)

    suspend fun deleteAll()
}