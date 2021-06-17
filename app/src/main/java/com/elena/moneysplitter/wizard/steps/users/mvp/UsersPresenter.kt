package com.elena.moneysplitter.wizard.steps.users.mvp

import com.elena.domain.user.UserEntity
import com.elena.domain.user.interaction.DeleteUserUseCase
import com.elena.domain.user.interaction.GetAllUsersUseCase
import com.elena.domain.user.interaction.SaveUserUseCase
import com.elena.moneysplitter.extras.CoroutineMvpPresenter
import com.elena.moneysplitter.wizard.mvp.PARAM_IS_STEP_READY
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.*
import moxy.MvpPresenter
import kotlin.coroutines.CoroutineContext

/**
 * @author elena
 */
class UsersPresenter(
        private val getAllUsersUseCase: GetAllUsersUseCase,
        private val deleteUserUseCase: DeleteUserUseCase,
        private val saveUserUseCase: SaveUserUseCase,
        private val router: Router
) : CoroutineMvpPresenter<UsersMvpView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        updateUsers()
    }

    fun onUserCreated(name: String) {
        launch {
            saveUserUseCase.execute(name, Unit)
            withContext(Dispatchers.Main) { updateUsers() }
        }
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