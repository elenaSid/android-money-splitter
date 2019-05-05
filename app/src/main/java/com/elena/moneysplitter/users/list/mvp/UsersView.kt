package com.elena.moneysplitter.users.list.mvp

import com.arellomobile.mvp.MvpView
import com.elena.domain.user.UserEntity

/**
 * @author elena
 *         Date: 04/01/2019
 *         Time: 09:38
 */
interface UsersView : MvpView {
    fun updateUsers(users: List<UserEntity>)
}