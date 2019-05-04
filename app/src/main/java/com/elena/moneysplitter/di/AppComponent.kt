package com.elena.moneysplitter.di

import android.content.Context
import com.elena.domain.family.FamilyRepository
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
@Component(modules = [(AndroidSupportInjectionModule::class), (AppModule::class),
    (DataModule::class), (BuilderModule::class)])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }

    fun familyRepository(): FamilyRepository
    fun inject(app: MoneySplitterApp)
}