package com.elena.moneysplitter.items.list.ui

import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.View
import com.elena.moneysplitter.PopupDialogFragment
import com.elena.moneysplitter.R
import com.elena.moneysplitter.databinding.ItemDialogFragmentBinding

/**
 * @author elena
 *         Date: 08/01/2019
 *         Time: 16:44
 */
class ItemDialogFragment : PopupDialogFragment() {

    private lateinit var binding: ItemDialogFragmentBinding


    override fun getTitle(): String {
        return "Item 1"
    }

    override fun getContent(): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.dialog_shopping_item,
                null, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvDebtors.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context, androidx.recyclerview.widget.LinearLayoutManager.VERTICAL, false)
        binding.rvDebtors.adapter = DebtorsAdapter()
    }
}