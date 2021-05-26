package com.elena.moneysplitter.wizard.steps.users.mvp

import com.elena.domain.user.UserEntity
import com.elena.domain.user.interaction.DeleteUserUseCase
import com.elena.domain.user.interaction.GetAllUsersUseCase
import com.elena.domain.user.interaction.SaveUserUseCase
import com.elena.moneysplitter.wizard.mvp.PARAM_IS_STEP_READY
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

/**
 * @author elena
 */
class UsersPresenter(
        private val getAllUsersUseCase: GetAllUsersUseCase,
        private val deleteUserUseCase: DeleteUserUseCase,
        private val saveUserUseCase: SaveUserUseCase,
        private val router: Router
) : MvpPresenter<UsersMvpView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        updateUsers()
    }

    fun onUserCreated(name: String) {
        saveUserUseCase.execute(name, Unit)
        updateUsers()
    }

    fun onUserDeleted(user: UserEntity) {
        deleteUserUseCase.execute(user)
        updateUsers()
    }

    private fun updateUsers() {
        val users = getAllUsersUseCase.execute(Unit, emptyList())
        viewState.updateUserList(users)
        router.sendResult(PARAM_IS_STEP_READY, users.size > 1)
    }
}