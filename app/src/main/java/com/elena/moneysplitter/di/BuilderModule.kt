package com.elena.moneysplitter.di

import com.elena.moneysplitter.root.di.RootModule
import com.elena.moneysplitter.root.di.RootScope
import com.elena.moneysplitter.root.ui.RootActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author elena
 *         Date: 11.06.2018
 *         Time: 19:34
 */
@Module
abstract class BuilderModule {
    @RootScope
    @ContributesAndroidInjector(modules = [(RootModule::class)])
    abstract fun bindSplitterActivity(): RootActivity
}