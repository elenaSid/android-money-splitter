package com.elena.moneysplitter.users.list.mvp

import com.elena.domain.user.UserEntity
import com.elena.domain.user.interaction.DeleteUserUseCase
import com.elena.domain.user.interaction.GetAllUsersUseCase
import moxy.MvpPresenter

/**
 * @author elena
 *         Date: 04/01/2019
 *         Time: 09:38
 */
class UsersPresenter(private val getAllUsersUseCase: GetAllUsersUseCase,
                     private val deleteUserUseCase: DeleteUserUseCase) : MvpPresenter<UsersView>() {

    override fun attachView(view: UsersView?) {
        super.attachView(view)
        updateUsers()
    }

    private fun updateUsers() {
        viewState.updateUsers(getAllUsersUseCase.execute(Unit, emptyList()))
    }

    fun onUserDeleted(user: UserEntity) {
        deleteUserUseCase.execute(user)
        updateUsers()
    }
}