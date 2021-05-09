package com.elena.moneysplitter.wizard.steps.summary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.elena.moneysplitter.R
import com.elena.moneysplitter.databinding.SummaryFragmentBinding
import moxy.MvpAppCompatFragment

/**
 * @author elena
 */
class SummaryFragment: MvpAppCompatFragment() {

    private lateinit var binding: SummaryFragmentBinding

            override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fr_summary, container, false)
        return binding.root
    }
}