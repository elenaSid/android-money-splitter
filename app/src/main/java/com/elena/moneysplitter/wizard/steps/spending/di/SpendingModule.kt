package com.elena.moneysplitter.wizard.steps.spending.di

import com.elena.domain.item.ItemRepository
import com.elena.domain.item.interaction.GetAllItemsUseCase
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
    fun provideSpendingPresenter(
            router: Router,
            getAllItemsUseCase: GetAllItemsUseCase
    ) = SpendingPresenter(router, getAllItemsUseCase)
}