package com.elena.moneysplitter.wizard.mvp

import com.elena.domain.user.interaction.RemoveAllDataUseCase
import com.elena.moneysplitter.extras.CoroutineMvpPresenter
import com.elena.moneysplitter.extras.UIPreferencesManager
import com.elena.moneysplitter.navigation.WizardNavigationScreen
import com.elena.moneysplitter.wizard.WizardStep
import com.elena.moneysplitter.wizard.canGoBack
import com.elena.moneysplitter.wizard.canGoForward
import com.elena.moneysplitter.wizard.canSkip
import com.github.terrakok.cicerone.ResultListener
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

const val PARAM_IS_STEP_READY = "is_step_ready"

/**
 * @author elena
 */
class WizardPresenter(
        private val removeAllDataUseCase: RemoveAllDataUseCase,
        private val uiPreferencesManager: UIPreferencesManager,
        private val router: Router
) : CoroutineMvpPresenter<WizardMvpView>() {

    private val steps = WizardStep.values()
    private val resultListener = ResultListener { isStepReady ->
        viewState.setActionButtonEnabled(isStepReady as Boolean)
        setStepReadyResultListener()
    }
    private var currentStep = WizardStep.values()[0]

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        if (!uiPreferencesManager.isIntroShown()) {
            router.newRootScreen(WizardNavigationScreen.intro())
            return
        }

        val savedStepIndex = uiPreferencesManager.getWizardProgress()
        if (savedStepIndex >= 0) {
            currentStep = WizardStep.values()[savedStepIndex]
        }
        setStep(currentStep)
    }

    /**
     * Вызывается при при ручном запросе на смену шага
     */
    fun onNextStepRequested() = setNextStep()

    /**
     * Вызывается при запросе предыдущего шага
     */
    fun onPreviousStepRequested() {
        if (currentStep.canGoBack()) {
            val nextStepIndex = currentStep.value - 1
            setStep(WizardStep.values()[nextStepIndex])
        } else {
            router.finishChain()
        }
    }

    fun onAddNewInstanceRequested() {
        if (currentStep == WizardStep.FAMILIES) {
            router.navigateTo(WizardNavigationScreen.familyEdit())
        } else if (currentStep == WizardStep.SPENDING) {
            router.navigateTo(WizardNavigationScreen.spendingEdit())
        }
    }

    fun onClearDataRequested() {
        launch {
            removeAllDataUseCase.execute(Unit)
            uiPreferencesManager.clearWizardProgress()
            withContext(Dispatchers.Main) { setStep(WizardStep.USERS) }
        }
    }

    /**
     * Устанавливает следующий шаг онбординга
     */
    private fun setNextStep() {
        if (currentStep.value == steps.size - 1) {
            viewState.showConfirmationDialog()
        } else {
            setStep(WizardStep.values()[currentStep.value + 1])
        }
    }

    /**
     * Устанавливает в качестве текущего шага онбординга указанный [wizardStep]
     */
    private fun setStep(wizardStep: WizardStep) {
        currentStep = wizardStep
        viewState.setHomeButtonVisibility(wizardStep.canGoBack())
        viewState.setActionButtonEnabled(wizardStep.canSkip())
        viewState.updateActionButton(!wizardStep.canGoForward())
        viewState.setFABVisibility(wizardStep.hasFAB)
        router.navigateTo(getStepFragmentScreen(wizardStep))
        setStepReadyResultListener()
        uiPreferencesManager.setWizardProgress(currentStep.value)
        viewState.updateStepCount(wizardStep.value + 1, WizardStep.values().size)
    }

    /**
     * Возвращает [FragmentScreen] для заданного шага [wizardStep]
     */
    private fun getStepFragmentScreen(wizardStep: WizardStep) = when (wizardStep) {
        WizardStep.USERS -> WizardNavigationScreen.usersStep()
        WizardStep.FAMILIES -> WizardNavigationScreen.familiesStep()
        WizardStep.SPENDING -> WizardNavigationScreen.spendingStep()
        WizardStep.SUMMARY -> WizardNavigationScreen.summaryStep()
        WizardStep.DEBTS -> WizardNavigationScreen.debtsStep()
    }

    /**
     * Устанавливает слушатель событий изменения готовности перехода на следующий шаг
     */
    private fun setStepReadyResultListener() {
        router.setResultListener(PARAM_IS_STEP_READY, resultListener)
    }
}