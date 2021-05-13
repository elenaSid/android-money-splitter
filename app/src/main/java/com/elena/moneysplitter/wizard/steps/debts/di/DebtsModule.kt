package com.elena.moneysplitter.wizard.steps.debts.di

import com.elena.domain.family.FamilyRepository
import com.elena.domain.item.ItemRepository
import com.elena.domain.summary.interaction.GetOptimizedTransactionsForAllFamiliesUseCase
import com.elena.domain.summary.interaction.GetSummaryForAllFamiliesUseCase
import com.elena.domain.summary.interaction.GetSummaryForAllUsersUseCase
import com.elena.domain.user.UserRepository
import com.elena.moneysplitter.wizard.steps.debts.mvp.DebtsPresenter
import dagger.Module
import dagger.Provides

/**
 * @author elena
 */
@Module
class DebtsModule {

    @Provides
    @DebtsScope
    fun provideGetSummaryForAllUsersUseCase(
            userRepository: UserRepository,
            itemRepository: ItemRepository
    ) = GetSummaryForAllUsersUseCase(userRepository, itemRepository)

    @Provides
    @DebtsScope
    fun provideGetSummaryForAllFamiliesUseCase(
            getSummaryForAllUsersUseCase: GetSummaryForAllUsersUseCase,
            userRepository: UserRepository,
            familyRepository: FamilyRepository
    ) = GetSummaryForAllFamiliesUseCase(
            getSummaryForAllUsersUseCase,
            userRepository,
            familyRepository
    )

    @Provides
    @DebtsScope
    fun provideGetOptimizedTransactionsForAllFamiliesUseCase(
            getSummaryForAllFamiliesUseCase: GetSummaryForAllFamiliesUseCase
    ) = GetOptimizedTransactionsForAllFamiliesUseCase(getSummaryForAllFamiliesUseCase)

    @Provides
    @DebtsScope
    fun provideDebtsPresenter(
            getOptimizedTransactionsForAllFamiliesUseCase: GetOptimizedTransactionsForAllFamiliesUseCase
    ) = DebtsPresenter(getOptimizedTransactionsForAllFamiliesUseCase)
}