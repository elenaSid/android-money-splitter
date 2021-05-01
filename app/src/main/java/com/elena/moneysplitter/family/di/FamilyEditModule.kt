package com.elena.moneysplitter.family.di

import com.elena.domain.family.FamilyRepository
import com.elena.domain.family.interaction.GetFamilyWithMembersUseCase
import com.elena.domain.family.interaction.SaveFamilyUseCase
import com.elena.domain.user.UserRepository
import com.elena.domain.user.interaction.GetUsersWithoutFamilyUseCase
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
    fun provideGetUsersWithoutFamilyUseCase(userRepository: UserRepository) =
            GetUsersWithoutFamilyUseCase(userRepository)

    @Provides
    @FamilyEditScope
    fun provideGetFamilyWithMembers(
            familyRepository: FamilyRepository,
            userRepository: UserRepository
    ) = GetFamilyWithMembersUseCase(familyRepository, userRepository)

    @Provides
    @FamilyEditScope
    fun provideFamilyEditPresenter(
            getUsersWithoutFamilyUseCase: GetUsersWithoutFamilyUseCase,
            getFamilyWithMembersUseCase: GetFamilyWithMembersUseCase,
            saveFamilyUseCase: SaveFamilyUseCase
    ) = FamilyEditPresenter(
            getUsersWithoutFamilyUseCase,
            getFamilyWithMembersUseCase,
            saveFamilyUseCase
    )
}