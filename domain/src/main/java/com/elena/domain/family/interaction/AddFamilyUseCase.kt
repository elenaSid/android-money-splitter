package com.elena.domain.family.interaction

import com.elena.domain.common.RxSingleUseCase
import com.elena.domain.family.FamilyEntity
import com.elena.domain.family.FamilyRepository
import io.reactivex.Single

/**
 * @author elena
 *         Date: 12/01/2019
 *         Time: 20:30
 */
class AddFamilyUseCase(private val familyRepository: FamilyRepository) : RxSingleUseCase<String, Unit>() {
    override fun runUseCase(param: String): Single<Unit> {
        return Single.fromCallable {
            familyRepository.save(FamilyEntity(name = param))
        }
    }
}