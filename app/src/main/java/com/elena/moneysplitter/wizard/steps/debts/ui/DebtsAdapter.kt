package com.elena.moneysplitter.wizard.steps.debts.ui

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
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
        holder.itemView.setOnLongClickListener {
            copyDebtToClipboard(holder.itemView.context, debt)
        }
    }

    override fun getItemCount(): Int {
        return debts.size
    }

    private fun copyDebtToClipboard(
            context: Context,
            debt: OptimizedTransactionForFamily
    ): Boolean {
        val text = String.format(
                context.getString(R.string.debt_copy_format),
                debt.debtorFamilyName,
                debt.creditorFamilyName,
                debt.debt
        )

        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText(context.getString(R.string.debts_title), text)
        clipboard.setPrimaryClip(clip)
        Toast.makeText(context, R.string.debts_copied, Toast.LENGTH_SHORT).show()
        return true
    }
}