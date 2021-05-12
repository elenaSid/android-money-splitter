package com.elena.moneysplitter.wizard.steps.summary.di

import com.elena.moneysplitter.wizard.steps.summary.mvp.SummaryPresenter
import dagger.Module
import dagger.Provides

/**
 * @author elena
 */
@Module
class SummaryModule {

    @Provides
    @SummaryScope
    fun provideSummaryPresenter() = SummaryPresenter()
}