package com.elena.moneysplitter.domain.check

/**
 * @author elena
 *         Date: 11.06.2018
 *         Time: 21:53
 */
class CheckEntity(id: Int, personId: Int, itemId: Int, payment: Double) {
    companion object {
        @JvmStatic var INVALID_ID: Int = -1
    }

    private var id: Int = INVALID_ID
    private var personId: Int
    private var itemId: Int
    private var payment: Double = 0.0

    init {
        this.id = id
        this.personId = personId
        this.itemId = itemId
        this.payment = payment
    }
}