package com.elena.domain.family.interaction

import com.elena.domain.common.exception.RxFlowableUseCase
import com.elena.domain.family.FamilyEntity
import com.elena.domain.family.FamilyRepository
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

/**
 * @author elena
 *         Date: 12/01/2019
 *         Time: 20:05
 */
class GetFamiliesUseCase(private val familyRepository: FamilyRepository) : RxFlowableUseCase<Unit, List<FamilyEntity>>() {

    override fun runUseCase(param: Unit): Flowable<List<FamilyEntity>> {
        return Flowable.just(familyRepository.getAll())
                .subscribeOn(Schedulers.io())
    }
}