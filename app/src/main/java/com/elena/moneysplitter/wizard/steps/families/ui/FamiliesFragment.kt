package com.elena.moneysplitter.wizard.steps.families.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.elena.domain.family.FamilyEntity
import com.elena.domain.family.FamilyMembers
import com.elena.moneysplitter.R
import com.elena.moneysplitter.databinding.FamiliesFragmentBinding
import com.elena.moneysplitter.wizard.steps.families.mvp.FamiliesMvpView
import com.elena.moneysplitter.wizard.steps.families.mvp.FamiliesPresenter
import dagger.android.support.AndroidSupportInjection
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

/**
 * @author elena
 */
class FamiliesFragment : MvpAppCompatFragment(), FamiliesMvpView {

    @Inject
    @InjectPresenter
    lateinit var presenter: FamiliesPresenter
    private lateinit var binding: FamiliesFragmentBinding

    @ProvidePresenter
    fun provideUsersPresenter() = presenter

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fr_families, container, false)
        return binding.root
    }

    override fun updateFamilies(families: List<FamilyMembers>) {
        binding.ivEmpty.alpha = 0f
    }

    override fun showEmptyState() {
        binding.ivEmpty.alpha = 1f
    }
}