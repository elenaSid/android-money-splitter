package com.elena.moneysplitter.users.edit.mvp

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.elena.domain.family.interaction.AddFamilyUseCase
import com.elena.domain.family.interaction.GetFamiliesUseCase
import com.elena.domain.user.UserEntity
import com.elena.domain.user.interaction.GetUserUseCase
import com.elena.domain.user.interaction.SaveUserUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * @author elena
 *         Date: 06/01/2019
 *         Time: 10:05
 */
@InjectViewState
class UserEditPresenter(private val getUserUseCase: GetUserUseCase,
                        private val saveUserUseCase: SaveUserUseCase,
                        private val addFamilyUseCase: AddFamilyUseCase,
                        private val getFamiliesUseCase: GetFamiliesUseCase) : MvpPresenter<UserEditView>() {

    private val compositeDisposable = CompositeDisposable()
    private var user: UserEntity? = null

    fun onGetUser(id: Int) {
        val user = getUserUseCase.execute(id)
        this.user = user
        viewState.setName(user.name)
        viewState.setFamily(user.familyName)
    }

    fun onFamilyListClicked() {
        val disposable = getFamiliesUseCase.execute(Unit, ArrayList())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ viewState.showFamilies(it) }, { it.printStackTrace() })
        compositeDisposable.add(disposable)
    }

    fun onFamilyAdded(family: String) {
        val disposable = addFamilyUseCase.execute(family, Unit).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        compositeDisposable.add(disposable)
    }

    fun onUserSave(userName: String) {
        //TODO: сохранять семью
        val params = SaveUserUseCase.UserParams(this.user, userName, null)
        saveUserUseCase.execute(params)
        viewState.finishWithOkResult()
    }
}