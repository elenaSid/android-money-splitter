package com.elena.moneysplitter.users.edit.mvp

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.elena.domain.family.FamilyEntity

/**
 * @author elena
 *         Date: 06/01/2019
 *         Time: 10:03
 */
@StateStrategyType(AddToEndSingleStrategy::class)
interface UserEditView : MvpView {

    fun setName(name: String)

    fun showFamilies(families: List<FamilyEntity>)

    fun setFamily(family: FamilyEntity)

    fun finishWithOkResult()
}