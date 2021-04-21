package com.elena.data.user

import com.elena.domain.user.UserEntity
import com.elena.data.common.TwoWayDataMapper

/**
 * @author elena
 *         Date: 12/01/2019
 *         Time: 19:40
 */
class UserDbMapper : TwoWayDataMapper<UserDbEntity, UserEntity> {

    override fun map2(data: UserEntity): UserDbEntity {
        return UserDbEntity(data.id, data.name, data.familyId)
    }

    override fun map(data: UserDbEntity): UserEntity {
        return UserEntity(data.id, data.name, data.familyId)
    }
}