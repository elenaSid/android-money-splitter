package com.elena.moneysplitter.wizard.steps.debts.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.elena.domain.summary.OptimizedTransactionForFamily
import com.elena.moneysplitter.R
import java.text.NumberFormat
import java.util.*

/**
 * @author elena
 */
class DebtsAdapter(
        private val debts: Set<OptimizedTransactionForFamily>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val numberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
                R.layout.view_debt_item,
                parent,
                false
        )
        return object : RecyclerView.ViewHolder(view) {}
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val debt = debts.elementAt(position)
        holder.itemView.findViewById<TextView>(R.id.tvDebtor).text = debt.debtorFamilyName
        holder.itemView.findViewById<TextView>(R.id.tvCreditor).text = debt.creditorFamilyName
        holder.itemView.findViewById<TextView>(R.id.tvDebt).text = numberFormat.format(debt.debt)
    }

    override fun getItemCount(): Int {
        return debts.size
    }
}