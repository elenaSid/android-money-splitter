package com.elena.moneysplitter.wizard.di

import com.elena.moneysplitter.wizard.mvp.WizardPresenter
import dagger.Module
import dagger.Provides

/**
 * @author elena
 */
@Module
class WizardModule {

    @Provides
    @WizardScope
    fun provideWizardPresenter() = WizardPresenter()
}