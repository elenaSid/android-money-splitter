package com.elena.moneysplitter.intro.mvp

import android.os.Handler
import android.os.Looper
import com.elena.moneysplitter.extras.UIPreferencesManager
import moxy.MvpPresenter
import java.util.*

/**
 * @author elena
 */
class IntroPresenter(
        private val uiPreferencesManager: UIPreferencesManager
) : MvpPresenter<IntroMvpView>() {

    private val handler = Handler(Looper.getMainLooper())
    private val update = Runnable { viewState.changeIntroSlide() }
    private val task = object : TimerTask() {
        override fun run() {
            handler.post(update)
        }
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        Timer().schedule(task, 2500, 2500)
    }

    override fun onDestroy() {
        super.onDestroy()
        task.cancel()
    }

    fun onGetStartedClicked() {
        uiPreferencesManager.setIntroShown()
        viewState.launchWizard()
    }
}