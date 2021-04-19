package com.elena.moneysplitter.items.list.ui

import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import com.elena.moneysplitter.utils.DisplayUtils

/**
 * @author elena
 *         Date: 08/01/2019
 *         Time: 22:33
 */
class DebtorsAdapter : androidx.recyclerview.widget.RecyclerView.Adapter<androidx.recyclerview.widget.RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): androidx.recyclerview.widget.RecyclerView.ViewHolder {
        val textView = TextView(parent.context)
        val padding16 = DisplayUtils.dpToPx(parent.context.resources, 16f)
        textView.setPadding(padding16, 0, padding16, 0)
        return object : androidx.recyclerview.widget.RecyclerView.ViewHolder(textView) {}
    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun onBindViewHolder(holder: androidx.recyclerview.widget.RecyclerView.ViewHolder, position: Int) {
        (holder.itemView as TextView).text = "User $position"
    }
}