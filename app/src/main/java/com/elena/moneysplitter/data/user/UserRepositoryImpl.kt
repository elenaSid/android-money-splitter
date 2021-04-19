package com.elena.moneysplitter.data.user

import com.elena.domain.user.UserEntity
import com.elena.domain.user.UserRepository
import com.elena.moneysplitter.data.common.TwoWayDataMapper

/**
 * @author elena
 *         Date: 2019-05-04
 *         Time: 17:34
 */
class UserRepositoryImpl(private val userDao: UserDao,
                         private val mapper: TwoWayDataMapper<UserDbEntity, UserEntity>) : UserRepository {
    override fun save(user: UserEntity) {
        userDao.insertOrReplace(mapper.map2(user))
    }

    override fun getAll(): List<UserEntity> {
        return userDao.getAll().map { mapper.map(it) }
    }

    override fun get(id: Int): UserEntity {
        return mapper.map(userDao.get(id))
    }

    override fun delete(user: UserEntity) {
        userDao.delete(mapper.map2(user))
    }
}