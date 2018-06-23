package com.elena.moneysplitter.splitter.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.elena.moneysplitter.domain.person.interactor.SavePersonUseCase
import com.elena.moneysplitter.splitter.view.SplitterView

/**
 * @author elena
 *         Date: 12.06.2018
 *         Time: 18:05
 */
@InjectViewState
class SplitterPresenter : MvpPresenter<SplitterView>() {
    //private val savePersonUseCase: SavePersonUseCase

    /*public constructor(savePersonUseCase: SavePersonUseCase) {
        this.savePersonUseCase = savePersonUseCase
    }*/

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        //val params: SavePersonUseCase.Params = SavePersonUseCase.Params("Bobby", null)
        //savePersonUseCase.execute(params, null)
        viewState.showEmptyView()
    }
}