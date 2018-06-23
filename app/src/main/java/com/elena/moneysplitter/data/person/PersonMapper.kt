package com.elena.moneysplitter.data.person

import com.elena.moneysplitter.data.common.TwoWayDataMapper
import com.elena.moneysplitter.domain.person.PersonEntity

/**
 * @author elena
 *         Date: 12.06.2018
 *         Time: 14:57
 */
class PersonMapper : TwoWayDataMapper<Person, PersonEntity> {

    override fun map2(data: PersonEntity): Person {
        val person = Person()
        person.id = data.id
        person.name = data.name
        person.photo = data.photo
        return person
    }

    override fun map(data: Person): PersonEntity {
        val person = PersonEntity()
        person.id = data.id
        person.name = data.name
        person.photo = data.photo
        return person
    }
}