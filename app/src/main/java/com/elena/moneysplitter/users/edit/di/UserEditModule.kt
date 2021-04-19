package com.elena.moneysplitter.users.edit.di

import com.elena.domain.family.FamilyRepository
import com.elena.domain.family.interaction.AddFamilyUseCase
import com.elena.domain.family.interaction.GetFamiliesUseCase
import com.elena.domain.family.interaction.GetFamilyUseCase
import com.elena.domain.user.UserRepository
import com.elena.domain.user.interaction.GetUserUseCase
import com.elena.domain.user.interaction.SaveUserUseCase
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
    fun provideGetUserUseCase(userRepository: UserRepository): GetUserUseCase {
        return GetUserUseCase(userRepository)
    }

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
    fun provideSaveUserUseCase(userRepository: UserRepository): SaveUserUseCase {
        return SaveUserUseCase(userRepository)
    }

    @Provides
    @UserEditScope
    fun provideGetFamilyUseCase(familyRepository: FamilyRepository): GetFamilyUseCase {
        return GetFamilyUseCase(familyRepository)
    }

    @Provides
    @UserEditScope
    fun provideUserEditPresenter(getUserUseCase: GetUserUseCase,
                                 saveUserUseCase: SaveUserUseCase,
                                 getFamilyUseCase: GetFamilyUseCase,
                                 addFamilyUseCase: AddFamilyUseCase,
                                 getFamiliesUseCase: GetFamiliesUseCase): UserEditPresenter {
        return UserEditPresenter(getUserUseCase, saveUserUseCase, getFamilyUseCase,
                addFamilyUseCase, getFamiliesUseCase)
    }
}