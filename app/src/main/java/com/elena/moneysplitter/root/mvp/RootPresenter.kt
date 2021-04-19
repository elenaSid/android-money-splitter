package com.elena.moneysplitter.root.mvp

import moxy.MvpPresenter

/**
 * @author elena
 *         Date: 12.06.2018
 *         Time: 18:05
 */
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