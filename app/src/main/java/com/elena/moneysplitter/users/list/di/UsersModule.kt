package com.elena.moneysplitter.users.list.di

import com.elena.moneysplitter.users.list.mvp.UsersPresenter
import dagger.Module
import dagger.Provides

/**
 * @author elena
 *         Date: 04/01/2019
 *         Time: 09:36
 */
@Module
class UsersModule {

    @Provides
    @UserScope
    fun provideUsersPresenter(): UsersPresenter {
        return UsersPresenter()
    }
}