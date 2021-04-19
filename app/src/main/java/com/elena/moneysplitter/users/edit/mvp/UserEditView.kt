package com.elena.moneysplitter.users.edit.mvp

import com.elena.domain.family.FamilyEntity
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

/**
 * @author elena
 *         Date: 06/01/2019
 *         Time: 10:03
 */
interface UserEditView : MvpView {

    @AddToEndSingle
    fun setName(name: String)

    @AddToEndSingle
    fun showFamilies(families: List<FamilyEntity>)

    @AddToEndSingle
    fun setFamily(family: FamilyEntity)

    @AddToEndSingle
    fun finishWithOkResult()
}