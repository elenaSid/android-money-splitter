package com.elena.moneysplitter.family.mvp

import com.elena.domain.user.UserEntity
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip

/**
 * @author elena
 */
interface FamilyEditMvpView : MvpView {

    @AddToEndSingle
    fun showEmptyState()

    @AddToEndSingle
    fun setFamilyName(familyName: String?)

    @AddToEndSingle
    fun updateFamilyMembers(users: List<UserEntity>, usersInFamily: List<UserEntity>)

    @AddToEndSingle
    fun manageSaveBtnAvailability(isEnabled: Boolean)

    @AddToEndSingle
    fun manageErrorMessage(hasNoError: Boolean)

    @Skip
    fun saveFinish()
}