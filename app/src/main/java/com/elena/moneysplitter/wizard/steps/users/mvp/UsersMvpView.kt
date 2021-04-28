package com.elena.moneysplitter.wizard.steps.users.mvp

import com.elena.domain.user.UserEntity
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

/**
 * @author elena
 */
interface UsersMvpView: MvpView {

    @AddToEndSingle
    fun updateUserList(users: List<UserEntity>)
}