package com.elena.moneysplitter.domain.person.interactor

import com.elena.moneysplitter.domain.common.UseCase
import com.elena.moneysplitter.domain.common.exception.ValidationException
import com.elena.moneysplitter.domain.person.PersonEntity
import com.elena.moneysplitter.domain.person.PersonRepository

/**
 * @author elena
 *         Date: 12.06.2018
 *         Time: 16:59
 */
class SavePersonUseCase(private val personRepository: PersonRepository) : UseCase<SavePersonUseCase.Params, Void>() {

    override fun buildUseCase(param: Params?): Void? {
        if (param == null) {
            throw ValidationException("No params to save person")
        }
        val person = PersonEntity()
        person.name = param.name
        person.photo = param.photo
        personRepository.save(person)
        return null
    }

    class Params(val name: String, val photo: String?)
}