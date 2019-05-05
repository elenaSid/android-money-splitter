package com.elena.moneysplitter.data.family

import com.elena.domain.family.FamilyEntity
import com.elena.domain.family.FamilyRepository
import com.elena.moneysplitter.data.common.TwoWayDataMapper

/**
 * @author elena
 *         Date: 12/01/2019
 *         Time: 19:17
 */
class FamilyRepositoryImpl(private val familyDao: FamilyDao,
                           private val mapper: TwoWayDataMapper<FamilyDbEntity, FamilyEntity>) : FamilyRepository {
    override fun getAll(): List<FamilyEntity> {
        return familyDao.getAll()
                .map { mapper.map(it) }
    }

    override fun save(family: FamilyEntity) {
        familyDao.insertOrReplace(mapper.map2(family))
    }
}