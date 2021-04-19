package com.elena.moneysplitter.users.list.mvp

import com.elena.domain.user.UserEntity
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

/**
 * @author elena
 *         Date: 04/01/2019
 *         Time: 09:38
 */
interface UsersView : MvpView {
    @AddToEndSingle
    fun updateUsers(users: List<UserEntity>)
}