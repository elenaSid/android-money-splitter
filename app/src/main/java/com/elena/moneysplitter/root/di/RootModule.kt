package com.elena.moneysplitter.root.di

import com.elena.moneysplitter.root.presenter.RootPresenter
import dagger.Module
import dagger.Provides

/**
 * @author elena
 *         Date: 12.06.2018
 *         Time: 18:02
 */
@Module
class RootModule {

    @Provides
    @RootScope
    fun provideSplitterPresenter(): RootPresenter {
        return RootPresenter()
    }
}