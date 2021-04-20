package com.elena.data.common

/**
 * @author elena
 *         Date: 12.06.2018
 *         Time: 14:53
 */
interface TwoWayDataMapper<FROM, TO> : DataMapper<FROM, TO> {
    /**
     * Mapping object
     *
     * @param data object to map
     * @return already mapped object
     */
    @Throws(DataMapperException::class)
    fun map2(data: TO): FROM
}
