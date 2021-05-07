package com.elena.moneysplitter.wizard.steps.spendings.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.elena.domain.item.ItemEntity
import com.elena.moneysplitter.R
import com.elena.moneysplitter.databinding.SpendingFragmentBinding
import com.elena.moneysplitter.wizard.steps.spendings.mvp.SpendingMvpView
import com.elena.moneysplitter.wizard.steps.spendings.mvp.SpendingPresenter
import dagger.android.support.AndroidSupportInjection
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

/**
 * @author elena
 */
class SpendingFragment : MvpAppCompatFragment(), SpendingMvpView {

    @Inject
    @InjectPresenter
    lateinit var presenter: SpendingPresenter
    private lateinit var binding: SpendingFragmentBinding

    @ProvidePresenter
    fun provideSpendingPresenter() = presenter

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fr_spending, container, false)
        return binding.root
    }

    override fun updateItems(items: List<ItemEntity>) {
        binding.ivEmpty.alpha = 0f
    }

    override fun showEmptyState() {
        binding.ivEmpty.alpha = 1f
    }
}