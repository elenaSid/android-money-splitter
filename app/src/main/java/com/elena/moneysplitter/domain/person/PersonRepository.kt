package com.elena.moneysplitter.domain.person

import com.elena.moneysplitter.domain.common.exception.RepositoryException

/**
 * @author elena
 *         Date: 12.06.2018
 *         Time: 15:08
 */
interface PersonRepository {
    /**
     * Get [PersonEntity] with selected id
     */
    fun get(id: Int): PersonEntity?

    /**
     * Create or update [PersonEntity]
     */
    @Throws(RepositoryException::class)
    fun save(person: PersonEntity)

    /**
     * Get all entities of [PersonEntity] as a [List]
     */
    fun list(): List<PersonEntity>
}