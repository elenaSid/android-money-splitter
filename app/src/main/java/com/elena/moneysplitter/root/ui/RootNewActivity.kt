package com.elena.moneysplitter.root.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.elena.moneysplitter.R
import com.elena.moneysplitter.databinding.RootNewActivityBinding
import moxy.MvpAppCompatActivity

/**
 * @author elena
 */
class RootNewActivity: MvpAppCompatActivity() {

    private lateinit var binding: RootNewActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.ac_root_new)
    }
}