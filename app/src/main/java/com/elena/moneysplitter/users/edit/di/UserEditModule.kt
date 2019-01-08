package com.elena.moneysplitter.users.edit.di

import com.elena.moneysplitter.users.edit.mvp.UserEditPresenter
import dagger.Module
import dagger.Provides

/**
 * @author elena
 *         Date: 06/01/2019
 *         Time: 10:03
 */
@Module
class UserEditModule {

    @Provides
    @UserEditScope
    fun provideUserEditPresenter(): UserEditPresenter {
        return UserEditPresenter()
    }
}