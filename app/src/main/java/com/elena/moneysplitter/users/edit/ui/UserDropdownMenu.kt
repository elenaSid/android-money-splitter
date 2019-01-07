package com.elena.moneysplitter.users.edit.ui

import android.content.Context
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.PopupWindow
import com.elena.moneysplitter.R
import com.elena.moneysplitter.utils.DisplayUtils


/**
 * @author elena
 *         Date: 07/01/2019
 *         Time: 10:05
 */
class UserDropdownMenu(private val context: Context?,
                       private val families: List<String>,
                       private val listener: FamilyAdapter.FamilyListener) : PopupWindow() {

    init {
        init()
    }

    private fun init() {
        if (context == null) {
            return
        }

        val view = LayoutInflater.from(context).inflate(R.layout.popup_family, null)
        val recyclerView = view.findViewById(R.id.rvFamilies) as RecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        recyclerView.adapter = FamilyAdapter(listener, families)
        contentView = view
    }
}