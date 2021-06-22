package com.elena.data.family

import com.elena.domain.family.FamilyEntity
import com.elena.domain.family.FamilyRepository
import com.elena.data.common.TwoWayDataMapper

/**
 * @author elena
 */
class FamilyRepositoryImpl(
        private val familyDao: FamilyDao,
        private val mapper: TwoWayDataMapper<FamilyDbEntity, FamilyEntity>
) : FamilyRepository {

    override suspend fun getAll(): List<FamilyEntity> {
        return familyDao.getAll().map { mapper.map(it) }
    }

    override suspend fun save(family: FamilyEntity) {
        familyDao.insertOrReplace(mapper.map2(family))
    }

    override suspend fun getLast(): FamilyEntity {
        return mapper.map(familyDao.getLast())
    }

    override suspend fun get(id: Int): FamilyEntity {
        return mapper.map(familyDao.get(id))
    }

    override suspend fun delete(family: FamilyEntity) {
        familyDao.delete(mapper.map2(family))
    }

    override suspend fun deleteAll() {
        familyDao.deleteAll()
    }
}