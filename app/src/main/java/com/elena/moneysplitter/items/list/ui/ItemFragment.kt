package com.elena.moneysplitter.items.list.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
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
class ItemFragment : MvpAppCompatFragment(), ItemAdapter.ItemListener {

    private lateinit var binding: ItemsFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fr_items, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initList()
    }

    private fun initList() {
        val adapter = ItemAdapter(this)
        binding.rvItems.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvItems.adapter = adapter
    }

    override fun onMoreClicked(anchor: View) {
    }

    override fun onItemClicked() {
    }
}