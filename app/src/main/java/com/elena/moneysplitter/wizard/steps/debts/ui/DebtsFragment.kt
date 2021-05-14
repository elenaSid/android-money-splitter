package com.elena.moneysplitter.wizard.steps.debts.ui

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.elena.domain.summary.OptimizedTransactionForFamily
import com.elena.moneysplitter.R
import com.elena.moneysplitter.databinding.DebtsFragmentBinding
import com.elena.moneysplitter.wizard.steps.debts.mvp.DebtsMvpView
import com.elena.moneysplitter.wizard.steps.debts.mvp.DebtsPresenter
import dagger.android.support.AndroidSupportInjection
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

/**
 * @author elena
 */
class DebtsFragment: MvpAppCompatFragment(), DebtsMvpView {

    @Inject
    @InjectPresenter
    lateinit var presenter: DebtsPresenter
    private lateinit var binding: DebtsFragmentBinding

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
        binding = DataBindingUtil.inflate(inflater, R.layout.fr_debts, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvDebts.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.btnCopyAll.setOnClickListener { presenter.onCopiedAsTextClicked() }
    }

    override fun updateDebts(debts: Set<OptimizedTransactionForFamily>) {
        binding.rvDebts.adapter = DebtsAdapter(debts)
    }

    override fun copyAllDebtsToClipboard(debts: Set<OptimizedTransactionForFamily>) {
        val text = debts.joinToString("\n") {
            String.format(
                    requireContext().getString(R.string.debt_copy_format),
                    it.debtorFamilyName,
                    it.creditorFamilyName,
                    it.debt
            )
        }
        val clipboard = requireActivity().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText(requireContext().getString(R.string.debts_title), text)
        clipboard.setPrimaryClip(clip)
        Toast.makeText(context, R.string.debts_copied, Toast.LENGTH_SHORT).show()
    }
}