package com.elena.moneysplitter.wizard.steps.spending.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.elena.moneysplitter.R
import com.elena.moneysplitter.databinding.SpendingFragmentBinding
import com.elena.moneysplitter.extras.FirstLastSpaceDecoration
import com.elena.moneysplitter.extras.toPx
import com.elena.moneysplitter.wizard.steps.spending.mvp.SpendingMvpView
import com.elena.moneysplitter.wizard.steps.spending.mvp.SpendingPresenter
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
    private val adapter = SpendingAdapter{presenter.onSpendingEditClicked(it)}

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvSpending.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val spaces = listOf(0, 0, 0, 76.toPx())
        binding.rvSpending.addItemDecoration(FirstLastSpaceDecoration(spaces))
        binding.rvSpending.adapter = adapter
    }

    override fun updateSpendingList(spendingList: List<SpendingData>) {
        binding.ivEmpty.alpha = 0f
        adapter.update(spendingList)
    }

    override fun showEmptyState() {
        binding.ivEmpty.alpha = 1f
        adapter.update(emptyList())
    }
}