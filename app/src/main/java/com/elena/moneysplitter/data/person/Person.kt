package com.elena.moneysplitter.data.person

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * @author elena
 *         Date: 12.06.2018
 *         Time: 14:26
 */
open class Person() : RealmObject() {

    @PrimaryKey
    open var id: Int = 0
    open var name: String? = null
    open var photo: String? = null
}