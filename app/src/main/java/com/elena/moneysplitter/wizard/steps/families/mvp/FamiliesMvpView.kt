package com.elena.moneysplitter.wizard.steps.families.mvp

import com.elena.domain.family.FamilyMembers
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

/**
 * @author elena
 */
interface FamiliesMvpView: MvpView {

    @AddToEndSingle
    fun updateFamilies(families: List<FamilyMembers>)

    @AddToEndSingle
    fun showEmptyState()
}