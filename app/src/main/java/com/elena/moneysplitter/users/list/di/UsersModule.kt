package com.elena.moneysplitter.users.list.di

import com.elena.domain.user.UserRepository
import com.elena.domain.user.interaction.DeleteUserUseCase
import com.elena.domain.user.interaction.GetAllUsersUseCase
import com.elena.domain.user.interaction.SaveUserUseCase
import com.elena.moneysplitter.users.list.mvp.UsersPresenter
import dagger.Module
import dagger.Provides

/**
 * @author elena
 *         Date: 04/01/2019
 *         Time: 09:36
 */
@Module
class UsersModule {

    @Provides
    @UserScope
    fun provideGetAllUsersUseCase(userRepository: UserRepository): GetAllUsersUseCase {
        return GetAllUsersUseCase(userRepository)
    }

    @Provides
    @UserScope
    fun provideSaveUserUseCase(userRepository: UserRepository): SaveUserUseCase {
        return SaveUserUseCase(userRepository)
    }

    @Provides
    @UserScope
    fun provideDeleteUserUseCase(userRepository: UserRepository): DeleteUserUseCase {
        return DeleteUserUseCase(userRepository)
    }

    @Provides
    @UserScope
    fun provideUsersPresenter(getAllUsersUseCase: GetAllUsersUseCase,
                              deleteUserUseCase: DeleteUserUseCase): UsersPresenter {
        return UsersPresenter(getAllUsersUseCase, deleteUserUseCase)
    }
}