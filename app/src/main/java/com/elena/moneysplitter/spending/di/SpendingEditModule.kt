package com.elena.moneysplitter.spending.di

import com.elena.domain.user.UserRepository
import com.elena.domain.user.interaction.GetAllUsersUseCase
import com.elena.moneysplitter.spending.mvp.SpendingEditPresenter
import dagger.Module
import dagger.Provides

/**
 * @author elena
 */
@Module
class SpendingEditModule {

    @Provides
    @SpendingEditScope
    fun provideGetAllUsersUseCase(
            userRepository: UserRepository
    ) = GetAllUsersUseCase(userRepository)

    @Provides
    @SpendingEditScope
    fun provide(
            getAllUsersUseCase: GetAllUsersUseCase
    ) = SpendingEditPresenter(getAllUsersUseCase)
}