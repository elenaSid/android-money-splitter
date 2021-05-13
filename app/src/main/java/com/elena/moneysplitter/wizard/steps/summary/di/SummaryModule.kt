package com.elena.moneysplitter.wizard.steps.summary.di

import com.elena.domain.family.FamilyRepository
import com.elena.domain.item.ItemRepository
import com.elena.domain.summary.interaction.GetSummaryForAllFamiliesUseCase
import com.elena.domain.summary.interaction.GetSummaryForAllUsersUseCase
import com.elena.domain.user.UserRepository
import com.elena.moneysplitter.wizard.steps.summary.mvp.SummaryPresenter
import dagger.Module
import dagger.Provides

/**
 * @author elena
 */
@Module
class SummaryModule {

    @Provides
    @SummaryScope
    fun provideGetSummaryForAllUsersUseCase(
            userRepository: UserRepository,
            itemRepository: ItemRepository
    ) = GetSummaryForAllUsersUseCase(userRepository, itemRepository)

    @Provides
    @SummaryScope
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
    @SummaryScope
    fun provideSummaryPresenter(
            getSummaryForAllFamiliesUseCase: GetSummaryForAllFamiliesUseCase
    ) = SummaryPresenter(getSummaryForAllFamiliesUseCase)
}