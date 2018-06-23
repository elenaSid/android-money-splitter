package com.elena.moneysplitter.data.person

import com.elena.moneysplitter.data.common.TwoWayDataMapper
import com.elena.moneysplitter.domain.common.exception.RepositoryException
import com.elena.moneysplitter.domain.person.PersonEntity
import com.elena.moneysplitter.domain.person.PersonRepository
import io.realm.Realm
import io.realm.RealmResults

/**
 * @author elena
 *         Date: 12.06.2018
 *         Time: 15:47
 */
class PersonRealmRepository(private val realm: Realm,
                            private val mapper: TwoWayDataMapper<Person, PersonEntity>) : PersonRepository {

    private fun getId(): Int {
        val number = realm.where(Person::class.java).max("id") ?: return 0
        return number.toInt() + 1
    }

    override fun get(id: Int): PersonEntity? {
        val person: Person = realm.where(Person::class.java).equalTo("id", id).findFirst()
                ?: throw RepositoryException("Person not found")
        return mapper.map(person)
    }

    override fun save(person: PersonEntity) {
        realm.beginTransaction()
        person.id = getId()
        realm.insertOrUpdate(mapper.map2(person))
        realm.commitTransaction()

        val p:List<PersonEntity> = list();
        val k:Int = p.size
    }

    override fun list(): List<PersonEntity> {
        val results:RealmResults<Person> = realm.where(Person::class.java).findAll()
        return results.map { mapper.map(it) }
    }
}