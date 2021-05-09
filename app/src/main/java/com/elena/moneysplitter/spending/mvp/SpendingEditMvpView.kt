package com.elena.moneysplitter.spending.mvp

import com.elena.domain.user.UserEntity
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip

/**
 * @author elena
 */
interface SpendingEditMvpView: MvpView {

    @AddToEndSingle
    fun setItemName(itemName: String?)

    @AddToEndSingle
    fun setItemPrice(itemPrice: Double)

    @AddToEndSingle
    fun updatePayerUsers(users: List<UserEntity>, selectedUsers: List<UserEntity>)

    @AddToEndSingle
    fun updateConsumerUsers(users: List<UserEntity>, selectedUsers: List<UserEntity>)

    @AddToEndSingle
    fun manageSaveBtnAvailability(isEnabled: Boolean)

    @Skip
    fun saveFinish()
}