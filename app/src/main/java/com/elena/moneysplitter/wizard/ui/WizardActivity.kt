package com.elena.moneysplitter.wizard.ui

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.elena.moneysplitter.R
import com.elena.moneysplitter.databinding.WizardActivityBinding
import com.elena.moneysplitter.navigation.NavigationLifecycleObserver
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
    lateinit var navigationLifecycleObserver: NavigationLifecycleObserver

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
        lifecycle.addObserver(navigationLifecycleObserver)

        binding.fabAdd.setOnClickListener {}
        binding.btnNext.setOnClickListener { presenter.onNextStepRequested() }
        binding.btnBack.setOnClickListener { presenter.onPreviousStepRequested() }
    }

    override fun onBackPressed() {
        presenter.onPreviousStepRequested()
    }

    override fun setHomeButtonVisibility(isHomeButtonVisible: Boolean) {
        binding.btnBack.visibility = if (isHomeButtonVisible) View.VISIBLE else View.GONE
    }

    override fun setFABVisibility(isVisible: Boolean) {
        if (isVisible) {
            binding.fabAdd.show()
        } else {
            binding.fabAdd.hide()
        }
        invalidateOptionsMenu()
    }

    override fun updateActionButton(isActionDone: Boolean) {
        binding.btnNext.setText(
                if (isActionDone) R.string.bottom_menu_done else R.string.bottom_menu_next
        )
    }

    override fun setActionButtonEnabled(isEnabled: Boolean) {
        binding.btnNext.isEnabled = isEnabled
    }
}