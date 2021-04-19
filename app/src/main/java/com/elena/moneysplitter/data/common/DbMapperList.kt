package com.elena.moneysplitter.data.common

/**
 * @author elena
 *         Date: 12/01/2019
 *         Time: 19:20
 */
class DbMapperList<T, E>(val list: List<T>, val dataMapper: DataMapper<T, E>) : AbstractList<E>() {

    override val size: Int get() = list.size

    override fun get(index: Int): E {
//        try {
            return dataMapper.map(list[index])
//        } catch (e: DataMapperException) {
//            return null
//        }
    }
}