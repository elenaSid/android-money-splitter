package com.elena.moneysplitter.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides

/**
 * @author elena
 */
@Module
class NavigationModule {
    @Provides
    @PerApplication
    fun provideCicerone() = Cicerone.create()

    @Provides
    fun provideRouter(cicerone: Cicerone<Router>) = cicerone.router

    @Provides
    fun provideNavigatorHolder(cicerone: Cicerone<Router>) = cicerone.getNavigatorHolder()

}