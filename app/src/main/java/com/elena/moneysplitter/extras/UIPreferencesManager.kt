package com.elena.moneysplitter.extras

import com.elena.domain.common.KeyValueStorage

private const val WIZARD_PROGRESS = "wizard_progress"
private const val IS_INTRO_SHOWN = "is_intro_shown"

/**
 * @author elena
 */
class UIPreferencesManager(private val keyValueStorage: KeyValueStorage) {

    fun getWizardProgress() = keyValueStorage.getValue(WIZARD_PROGRESS, -1)

    fun setWizardProgress(progress: Int) = keyValueStorage.setValue(WIZARD_PROGRESS, progress)

    fun clearWizardProgress() = keyValueStorage.remove(WIZARD_PROGRESS)

    fun isIntroShown() = keyValueStorage.getValue(IS_INTRO_SHOWN, false)

    fun setIntroShown() = keyValueStorage.setValue(IS_INTRO_SHOWN, true)
}