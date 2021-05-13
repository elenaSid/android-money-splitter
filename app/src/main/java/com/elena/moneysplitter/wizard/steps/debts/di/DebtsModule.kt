package com.elena.moneysplitter.wizard.steps.debts.di

import com.elena.moneysplitter.wizard.steps.debts.mvp.DebtsPresenter
import dagger.Module
import dagger.Provides

/**
 * @author elena
 */
@Module
class DebtsModule {

    @Provides
    @DebtsScope
    fun provideDebtsPresenter() = DebtsPresenter()
}