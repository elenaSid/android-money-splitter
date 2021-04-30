package com.elena.moneysplitter.family.di

import com.elena.domain.family.FamilyRepository
import com.elena.domain.family.interaction.SaveFamilyUseCase
import com.elena.domain.user.UserRepository
import com.elena.domain.user.interaction.GetAllUsersUseCase
import com.elena.moneysplitter.family.mvp.FamilyEditPresenter
import dagger.Module
import dagger.Provides

/**
 * @author elena
 */
@Module
class FamilyEditModule {

    @Provides
    @FamilyEditScope
    fun provideSaveFamilyUseCase(
            familyRepository: FamilyRepository,
            userRepository: UserRepository
    ) = SaveFamilyUseCase(familyRepository, userRepository)

    @Provides
    @FamilyEditScope
    fun provideGetAllUsersUseCase(userRepository: UserRepository) =
            GetAllUsersUseCase(userRepository)

    @Provides
    @FamilyEditScope
    fun provideFamilyEditPresenter(
            getAllUsersUseCase: GetAllUsersUseCase,
            saveFamilyUseCase: SaveFamilyUseCase
    ) = FamilyEditPresenter(getAllUsersUseCase, saveFamilyUseCase)
}