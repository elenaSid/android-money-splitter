package com.elena.moneysplitter.wizard.ui

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.elena.moneysplitter.R
import com.elena.moneysplitter.databinding.WizardActivityBinding
import com.elena.moneysplitter.wizard.mvp.WizardMvpView
import com.elena.moneysplitter.wizard.mvp.WizardPresenter
import dagger.android.AndroidInjection
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

/**
 * @author elena
 */
class WizardActivity : MvpAppCompatActivity(), WizardMvpView {

    @Inject
    @InjectPresenter
    lateinit var presenter: WizardPresenter
    private lateinit var binding: WizardActivityBinding

    @ProvidePresenter
    fun provideWizardPresenter() = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.ac_wizard)

        binding.fabAdd.setOnClickListener {
        }
    }

    override fun onBackPressed() {
        presenter.onPreviousStepRequested()
    }

    override fun setHomeButtonVisibility(isHomeButtonVisible: Boolean) {
        binding.btnBack.visibility = if (isHomeButtonVisible) View.VISIBLE else View.GONE
    }

    override fun setActionButtonEnabled(isEnabled: Boolean) {
        binding.btnNext.isEnabled = isEnabled
    }

    override fun setStep(stepIndex: Int) {
    }

    private fun manageFAB(isVisible: Boolean) {
        if (isVisible) {
            binding.fabAdd.show()
        } else {
            binding.fabAdd.hide()
        }
        invalidateOptionsMenu()
    }
}