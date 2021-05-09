package com.elena.moneysplitter.spending.di

import com.elena.domain.item.ItemRepository
import com.elena.domain.item.interaction.DeleteItemUseCase
import com.elena.domain.item.interaction.GetItemUseCase
import com.elena.domain.item.interaction.SaveItemUseCase
import com.elena.domain.user.UserRepository
import com.elena.domain.user.interaction.GetAllUsersUseCase
import com.elena.domain.user.interaction.GetUsersUseCase
import com.elena.moneysplitter.spending.mvp.SpendingEditPresenter
import dagger.Module
import dagger.Provides

/**
 * @author elena
 */
@Module
class SpendingEditModule {

    @Provides
    @SpendingEditScope
    fun provideGetAllUsersUseCase(
            userRepository: UserRepository
    ) = GetAllUsersUseCase(userRepository)

    @Provides
    @SpendingEditScope
    fun provideGetUsersUseCase(
            userRepository: UserRepository
    ) = GetUsersUseCase(userRepository)

    @Provides
    @SpendingEditScope
    fun provideSaveItemUseCase(
            itemRepository: ItemRepository
    ) = SaveItemUseCase(itemRepository)

    @Provides
    @SpendingEditScope
    fun provideGetItemUseCase(
            itemRepository: ItemRepository
    ) = GetItemUseCase(itemRepository)

    @Provides
    @SpendingEditScope
    fun provideDeleteItemUseCase(
            itemRepository: ItemRepository
    ) = DeleteItemUseCase(itemRepository)

    @Provides
    @SpendingEditScope
    fun provide(
            getAllUsersUseCase: GetAllUsersUseCase,
            deleteItemUseCase: DeleteItemUseCase,
            saveItemUseCase: SaveItemUseCase,
            getUsersUseCase: GetUsersUseCase,
            getItemUseCase: GetItemUseCase
    ) = SpendingEditPresenter(
            getAllUsersUseCase,
            deleteItemUseCase,
            saveItemUseCase,
            getUsersUseCase,
            getItemUseCase
    )
}