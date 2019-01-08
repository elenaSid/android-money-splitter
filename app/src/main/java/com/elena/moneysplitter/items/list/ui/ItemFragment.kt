package com.elena.moneysplitter.items.list.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.elena.moneysplitter.R
import com.elena.moneysplitter.databinding.ItemsFragmentBinding

/**
 * @author elena
 *         Date: 08/01/2019
 *         Time: 15:29
 */
class ItemFragment : MvpAppCompatFragment() {

    private lateinit var binding: ItemsFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fr_items, container, false)
        return binding.root
    }
}