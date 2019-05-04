package com.elena.moneysplitter.users.edit.mvp

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.elena.domain.family.interaction.AddFamilyUseCase
import com.elena.domain.family.interaction.GetFamiliesUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * @author elena
 *         Date: 06/01/2019
 *         Time: 10:05
 */
@InjectViewState
class UserEditPresenter(private val addFamilyUseCase: AddFamilyUseCase,
                        private val getFamiliesUseCase: GetFamiliesUseCase) : MvpPresenter<UserEditView>() {

    private val compositeDisposable = CompositeDisposable()

    //TODO: получать идентификатор пользователя
    fun onGetUser(name: String?, family: String?) {
        if (name != null) {
            viewState.setName(name)
        }

        if (family != null) {
            viewState.setFamily(family)
        }
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
}