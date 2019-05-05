package com.elena.domain.user

/**
 * @author elena
 *         Date: 08/01/2019
 *         Time: 12:42
 */
interface UserRepository {
    fun getAll() : List<UserEntity>

    fun save(user: UserEntity)

    fun get(id: Int): UserEntity

    fun delete(user: UserEntity)
}