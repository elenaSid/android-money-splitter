package com.elena.moneysplitter.domain.item

/**
 * @author elena
 *         Date: 11.06.2018
 *         Time: 21:49
 */
class ItemEntity {
    companion object {
        @JvmStatic var INVALID_ID: Int = -1
    }

    private var id: Int = INVALID_ID
    private var cost: Double = 0.0
    private var name: String? = null


    fun setId(id: Int) {
        this.id = id
    }

    fun getId(): Int {
        return id
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getName(): String? {
        return name
    }

    fun setCost(cost: Double) {
        this.cost = cost
    }

    fun getCost(): Double {
        return cost
    }
}