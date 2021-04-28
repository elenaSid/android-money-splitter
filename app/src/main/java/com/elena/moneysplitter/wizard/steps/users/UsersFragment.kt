package com.elena.moneysplitter.wizard.steps.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.elena.moneysplitter.R
import com.elena.moneysplitter.databinding.UsersWizardFragmentBinding
import moxy.MvpAppCompatFragment

/**
 * @author elena
 */
class UsersFragment: MvpAppCompatFragment() {

    private lateinit var binding: UsersWizardFragmentBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fr_wizard_users, container, false)
        return binding.root
    }
}