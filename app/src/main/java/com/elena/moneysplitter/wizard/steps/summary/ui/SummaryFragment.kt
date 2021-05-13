package com.elena.moneysplitter.wizard.steps.summary.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.elena.domain.summary.SummaryForFamily
import com.elena.moneysplitter.R
import com.elena.moneysplitter.databinding.SummaryFragmentBinding
import com.elena.moneysplitter.wizard.steps.summary.mvp.SummaryMvpView
import com.elena.moneysplitter.wizard.steps.summary.mvp.SummaryPresenter
import dagger.android.support.AndroidSupportInjection
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

/**
 * @author elena
 */
class SummaryFragment: MvpAppCompatFragment(), SummaryMvpView {

    @Inject
    @InjectPresenter
    lateinit var presenter: SummaryPresenter
    private lateinit var binding: SummaryFragmentBinding

    private val adapter = SummaryForFamilyAdapter()

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
        binding = DataBindingUtil.inflate(inflater, R.layout.fr_summary, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvSummary.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvSummary.adapter = adapter
    }

    override fun updateSummaryList(summarySet: Set<SummaryForFamily>) {
        adapter.update(summarySet)
    }
}