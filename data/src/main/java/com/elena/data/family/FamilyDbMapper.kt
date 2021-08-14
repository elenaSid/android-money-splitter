package com.elena.data.family

import com.elena.domain.family.FamilyEntity
import com.elena.data.common.TwoWayDataMapper

/**
 * @author elena
 */
class FamilyDbMapper : TwoWayDataMapper<FamilyDbEntity, FamilyEntity> {

    override fun map2(data: FamilyEntity): FamilyDbEntity {
        return FamilyDbEntity(data.id, data.name)
    }

    override fun map(data: FamilyDbEntity): FamilyEntity {
        return FamilyEntity(data.id, data.family)
    }
}