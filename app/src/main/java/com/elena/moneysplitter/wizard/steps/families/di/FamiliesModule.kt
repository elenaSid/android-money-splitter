package com.elena.moneysplitter.wizard.steps.families.di

import com.elena.domain.family.FamilyRepository
import com.elena.domain.family.interaction.GetAllFamiliesWithMembersUseCase
import com.elena.domain.user.UserRepository
import com.elena.moneysplitter.wizard.steps.families.mvp.FamiliesPresenter
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides

/**
 * @author elena
 */
@Module
class FamiliesModule {

    @Provides
    @FamiliesScope
    fun provideGetAllFamiliesWithMembersUseCase(
            familyRepository: FamilyRepository,
            userRepository: UserRepository
    ) = GetAllFamiliesWithMembersUseCase(familyRepository, userRepository)

    @Provides
    @FamiliesScope
    fun provideFamiliesPresenter(
            router: Router,
            getAllFamiliesWithMembersUseCase: GetAllFamiliesWithMembersUseCase
    ) = FamiliesPresenter(router, getAllFamiliesWithMembersUseCase)
}