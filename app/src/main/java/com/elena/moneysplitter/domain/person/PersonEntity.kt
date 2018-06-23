package com.elena.moneysplitter.domain.person

/**
 * @author elena
 *         Date: 11.06.2018
 *         Time: 21:06
 */
class PersonEntity {
    companion object {
        @JvmStatic
        var INVALID_ID: Int = -1
    }
    var id: Int = INVALID_ID
    var name: String? = null
    var photo: String? = null
}