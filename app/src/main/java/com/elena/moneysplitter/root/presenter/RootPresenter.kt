package com.elena.moneysplitter.root.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.elena.moneysplitter.root.view.RootView

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