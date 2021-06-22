package com.elena.data.user

import com.elena.domain.user.UserEntity
import com.elena.domain.user.UserRepository
import com.elena.data.common.TwoWayDataMapper

/**
 * @author elena
 */
class UserRepositoryImpl(
        private val userDao: UserDao,
        private val mapper: TwoWayDataMapper<UserDbEntity, UserEntity>
) : UserRepository {

    override suspend fun save(user: UserEntity) {
        userDao.insertOrReplace(mapper.map2(user))
    }

    override suspend fun getAll(): List<UserEntity> {
        return userDao.getAll().map { mapper.map(it) }
    }

    override suspend fun getUsersWithFamily(familyId: Int): List<UserEntity> {
        return userDao.getUsersWithFamily(familyId).map { mapper.map(it) }
    }

    override suspend fun getUsersWithoutFamily(): List<UserEntity> {
        return userDao.getUsersWithoutFamily().map { mapper.map(it) }
    }

    override suspend fun get(id: Int): UserEntity {
        return mapper.map(userDao.get(id))
    }

    override suspend fun delete(user: UserEntity) {
        userDao.delete(mapper.map2(user))
    }

    override suspend fun deleteAll() {
        userDao.deleteAll()
    }
}