package com.elena.moneysplitter.root.mvp

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter

/**
 * @author elena
 *         Date: 12.06.2018
 *         Time: 18:05
 */
@InjectViewState
class RootPresenter : MvpPresenter<RootView>() {
    //private val savePersonUseCase: SavePersonUseCase

    /*public constructor(savePersonUseCase: SavePersonUseCase) {
        this.savePersonUseCase = savePersonUseCase
    }*/

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showEmptyView()
    }
}