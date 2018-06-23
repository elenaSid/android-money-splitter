package com.elena.moneysplitter.di

import com.elena.moneysplitter.splitter.di.SplitterModule
import com.elena.moneysplitter.splitter.di.SplitterScope
import com.elena.moneysplitter.splitter.ui.SplitterActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author elena
 *         Date: 11.06.2018
 *         Time: 19:34
 */
@Module
abstract class BuilderModule {
    @SplitterScope
    @ContributesAndroidInjector(modules = [(SplitterModule::class)])
    abstract fun bindSplitterActivity(): SplitterActivity
}