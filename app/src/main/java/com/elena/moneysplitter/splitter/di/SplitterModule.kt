package com.elena.moneysplitter.splitter.di

import com.elena.moneysplitter.domain.person.PersonRepository
import com.elena.moneysplitter.domain.person.interactor.SavePersonUseCase
import com.elena.moneysplitter.splitter.presenter.SplitterPresenter
import dagger.Module
import dagger.Provides

/**
 * @author elena
 *         Date: 12.06.2018
 *         Time: 18:02
 */
@Module
class SplitterModule {
    @Provides
    @SplitterScope
    fun provideSavePersonUseCase(personRepository: PersonRepository): SavePersonUseCase {
        return SavePersonUseCase(personRepository)
    }

    @Provides
    @SplitterScope
    fun provideSplitterPresenter(savePersonUseCase: SavePersonUseCase) : SplitterPresenter {
        return SplitterPresenter()
    }
}