package com.elena.moneysplitter.wizard.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.elena.moneysplitter.R
import com.elena.moneysplitter.databinding.WizardActivityBinding
import com.elena.moneysplitter.extras.KeyboardManager
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
    private var confirmationDialog: AlertDialog? = null

    @ProvidePresenter
    fun provideWizardPresenter() = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.ac_wizard)
        lifecycle.addObserver(navigationLifecycleObserver)

        binding.fabAdd.setOnClickListener { presenter.onAddNewInstanceRequested() }
        binding.btnNext.setOnClickListener {
            presenter.onNextStepRequested()
            KeyboardManager.hide(this, binding.root.windowToken)
        }
        binding.btnBack.setOnClickListener {
            presenter.onPreviousStepRequested()
            KeyboardManager.hide(this, binding.root.windowToken)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        confirmationDialog?.dismiss()
    }

    override fun onBackPressed() {
        KeyboardManager.hide(this, binding.root.windowToken)
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

    override fun showConfirmationDialog() {
        confirmationDialog = AlertDialog.Builder(this)
                .setMessage(R.string.wizard_clear_data)
                .setPositiveButton(android.R.string.ok) { dialog, _ ->
                    presenter.onClearDataRequested()
                    dialog.dismiss()
                }
                .setNegativeButton(android.R.string.cancel) { dialog, _ ->
                    dialog.dismiss()
                }
                .setCancelable(true)
                .show()
    }

    override fun updateStepCount(currentStepIndex: Int, stepsCount: Int) {
        binding.tvSteps.text = getString(R.string.wizard_step_of, currentStepIndex, stepsCount)
    }
}