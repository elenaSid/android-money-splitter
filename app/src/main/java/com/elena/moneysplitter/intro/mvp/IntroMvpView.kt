package com.elena.moneysplitter.intro.mvp

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip

/**
 * @author elena
 */
interface IntroMvpView: MvpView {

    @AddToEndSingle
    fun changeIntroSlide()

    @Skip
    fun launchWizard()
}