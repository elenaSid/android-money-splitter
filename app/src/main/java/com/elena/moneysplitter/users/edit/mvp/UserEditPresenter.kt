package com.elena.moneysplitter.users.edit.mvp

import com.elena.domain.family.FamilyEntity
import com.elena.domain.family.interaction.AddFamilyUseCase
import com.elena.domain.family.interaction.GetFamiliesUseCase
import com.elena.domain.family.interaction.GetFamilyUseCase
import com.elena.domain.user.UserEntity
import com.elena.domain.user.interaction.GetUserUseCase
import com.elena.domain.user.interaction.SaveUserUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.MvpPresenter

/**
 * @author elena
 *         Date: 06/01/2019
 *         Time: 10:05
 */
class UserEditPresenter(private val getUserUseCase: GetUserUseCase,
                        private val saveUserUseCase: SaveUserUseCase,
                        private val getFamilyUseCase: GetFamilyUseCase,
                        private val addFamilyUseCase: AddFamilyUseCase,
                        private val getFamiliesUseCase: GetFamiliesUseCase) : MvpPresenter<UserEditView>() {

    private val compositeDisposable = CompositeDisposable()
    private var user: UserEntity? = null

    fun onGetUser(id: Int) {
        val user = getUserUseCase.execute(id)
        this.user = user
        val familyId = user.familyId
        if (familyId != null) {
            val family = getFamilyUseCase.execute(familyId)
            viewState.setFamily(family)
        }
        viewState.setName(user.name)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    fun onFamilyListClicked() {
    }

    fun onFamilyAdded(familyName: String) {
        val family = addFamilyUseCase.execute(familyName)
        viewState.setFamily(family)
    }

    fun onUserSave(userName: String, family: FamilyEntity) {
        /*val params = SaveUserUseCase.UserParams(this.user, userName, family)
        saveUserUseCase.execute(params)
        viewState.finishWithOkResult()*/
    }
}