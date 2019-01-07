package com.elena.moneysplitter.users.edit.mvp

import com.arellomobile.mvp.MvpView

/**
 * @author elena
 *         Date: 06/01/2019
 *         Time: 10:03
 */
interface UserEditView : MvpView {

    fun setName(name: String)

    fun setFamily(family: String)
}