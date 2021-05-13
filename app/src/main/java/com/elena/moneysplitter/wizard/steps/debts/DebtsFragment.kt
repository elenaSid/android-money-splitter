package com.elena.moneysplitter.wizard.steps.debts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.elena.moneysplitter.R
import com.elena.moneysplitter.databinding.DebtsFragmentBinding
import moxy.MvpAppCompatFragment

/**
 * @author elena
 */
class DebtsFragment: MvpAppCompatFragment() {

    private lateinit var binding: DebtsFragmentBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fr_debts, container, false)
        return binding.root
    }
}