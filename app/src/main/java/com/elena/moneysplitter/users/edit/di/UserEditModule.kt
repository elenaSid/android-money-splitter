package com.elena.moneysplitter.users.edit.di

import com.elena.domain.family.FamilyRepository
import com.elena.domain.family.interaction.AddFamilyUseCase
import com.elena.domain.family.interaction.GetFamiliesUseCase
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
    fun provideAddFamilyUseCase(familyRepository: FamilyRepository): AddFamilyUseCase {
        return AddFamilyUseCase(familyRepository)
    }

    @Provides
    @UserEditScope
    fun provideGetFamiliesUseCase(familyRepository: FamilyRepository): GetFamiliesUseCase {
        return GetFamiliesUseCase(familyRepository)
    }

    @Provides
    @UserEditScope
    fun provideUserEditPresenter(addFamilyUseCase: AddFamilyUseCase,
                                 getFamiliesUseCase: GetFamiliesUseCase): UserEditPresenter {
        return UserEditPresenter(addFamilyUseCase, getFamiliesUseCase)
    }
}