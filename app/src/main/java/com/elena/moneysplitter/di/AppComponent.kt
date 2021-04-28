package com.elena.moneysplitter.di

import android.app.Application
import com.elena.domain.family.FamilyRepository
import com.elena.domain.user.UserRepository
import com.elena.moneysplitter.MoneySplitterApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

/**
 * @author elena
 *         Date: 11.06.2018
 *         Time: 19:30
 */
@PerApplication
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    DataModule::class,
    NavigationModule::class,
    BuilderModule::class])
interface AppComponent {

    fun appContext(): Application

    fun familyRepository(): FamilyRepository
    fun userRepository(): UserRepository
    fun inject(app: MoneySplitterApp)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun app(application: Application): Builder

        fun build(): AppComponent
    }
}