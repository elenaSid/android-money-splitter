package com.elena.moneysplitter.intro.mvp

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

/**
 * @author elena
 */
interface IntroMvpView: MvpView {

    @AddToEndSingle
    fun changeIntroSlide()
}