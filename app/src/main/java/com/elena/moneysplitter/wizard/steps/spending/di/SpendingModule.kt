package com.elena.moneysplitter.wizard.steps.spending.di

import com.elena.domain.item.ItemRepository
import com.elena.domain.item.interaction.GetAllItemsUseCase
import com.elena.domain.user.UserRepository
import com.elena.domain.user.interaction.GetUsersUseCase
import com.elena.moneysplitter.wizard.steps.spending.mvp.SpendingPresenter
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides

/**
 * @author elena
 */
@Module
class SpendingModule {

    @Provides
    @SpendingScope
    fun provideGetAllItemsUseCase(
            itemRepository: ItemRepository
    ) = GetAllItemsUseCase(itemRepository)

    @Provides
    @SpendingScope
    fun provideGetUsersUseCase(userRepository: UserRepository) = GetUsersUseCase(userRepository)

    @Provides
    @SpendingScope
    fun provideSpendingPresenter(
            router: Router,
            getUsersUseCase: GetUsersUseCase,
            getAllItemsUseCase: GetAllItemsUseCase
    ) = SpendingPresenter(router, getUsersUseCase, getAllItemsUseCase)
}