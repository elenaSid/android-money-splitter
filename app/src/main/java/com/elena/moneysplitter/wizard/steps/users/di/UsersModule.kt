package com.elena.moneysplitter.wizard.steps.users.di

import com.elena.domain.user.UserRepository
import com.elena.domain.user.interaction.DeleteUserUseCase
import com.elena.domain.user.interaction.GetAllUsersUseCase
import com.elena.domain.user.interaction.SaveUserUseCase
import com.elena.moneysplitter.wizard.steps.users.mvp.UsersPresenter
import dagger.Module
import dagger.Provides

/**
 * @author elena
 */
@Module
class UsersModule {

    @Provides
    @UsersScope
    fun provideGetAllUsersUseCase(userRepository: UserRepository) = GetAllUsersUseCase(userRepository)

    @Provides
    @UsersScope
    fun provideSaveUserUseCase(userRepository: UserRepository) = SaveUserUseCase(userRepository)

    @Provides
    @UsersScope
    fun provideDeleteUserUseCase(userRepository: UserRepository) = DeleteUserUseCase(userRepository)

    @Provides
    @UsersScope
    fun provideUsersPresenter(
            getAllUsersUseCase: GetAllUsersUseCase,
            deleteUserUseCase: DeleteUserUseCase,
            saveUserUseCase: SaveUserUseCase
    ) = UsersPresenter(getAllUsersUseCase, deleteUserUseCase, saveUserUseCase)
}