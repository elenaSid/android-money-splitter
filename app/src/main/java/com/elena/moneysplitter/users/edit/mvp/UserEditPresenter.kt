package com.elena.moneysplitter.users.edit.mvp

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter

/**
 * @author elena
 *         Date: 06/01/2019
 *         Time: 10:05
 */
@InjectViewState
class UserEditPresenter : MvpPresenter<UserEditView>() {

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
        //TODO: получать настоящий список семей
        val list = ArrayList<String>()
        list.add("West\'s")
        viewState.showFamilies(list)
    }

    fun onFamilyAdded(family: String) {
        //TODO: вызывать сценарий сохранения новой семьи
    }
}