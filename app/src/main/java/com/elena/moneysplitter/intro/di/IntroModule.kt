package com.elena.moneysplitter.intro.di

import com.elena.domain.common.KeyValueStorage
import com.elena.moneysplitter.extras.UIPreferencesManager
import com.elena.moneysplitter.intro.mvp.IntroPresenter
import dagger.Module
import dagger.Provides

/**
 * @author elena
 */
@Module
class IntroModule {

    @Provides
    @IntroScope
    fun provideUiPreferencesManager(
            keyValueStorage: KeyValueStorage
    ) = UIPreferencesManager(keyValueStorage)

    @Provides
    @IntroScope
    fun provideIntroPresenter(
        uiPreferencesManager: UIPreferencesManager
    ) = IntroPresenter(uiPreferencesManager)
}