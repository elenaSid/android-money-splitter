package com.elena.moneysplitter.users.edit.ui

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.widget.PopupWindow
import com.elena.moneysplitter.R


/**
 * @author elena
 *         Date: 07/01/2019
 *         Time: 10:05
 */
class UserDropdownMenu(private val context: Context?,
                       private val families: List<String>,
                       private val onItemSelected: (String) -> Unit,
                       private val onAddFamily: () -> Unit) : PopupWindow(), FamilyAdapter.FamilyListener {
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
        recyclerView.adapter = FamilyAdapter(this, families)
        contentView = view
    }

    override fun onItemClicked(family: String) {
        onItemSelected(family)
        this.dismiss()
    }

    override fun onAddClicked() {
        onAddFamily()
        this.dismiss()
    }

}