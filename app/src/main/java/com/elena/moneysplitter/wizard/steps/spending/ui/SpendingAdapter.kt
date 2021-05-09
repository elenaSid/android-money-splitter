package com.elena.moneysplitter.wizard.steps.spending.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.elena.moneysplitter.R
import java.text.NumberFormat
import java.util.*

/**
 * @author elena
 */
class SpendingAdapter(
        private val listener: SpendingListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var spendingList = emptyList<SpendingData>()
    private val numberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_spending_item, parent, false)
        return object : RecyclerView.ViewHolder(view) {}
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val spending = spendingList[position]
        holder.itemView.findViewById<TextView>(R.id.tvItemName).text = spending.name
        holder.itemView.findViewById<TextView>(R.id.tvPrice).text = numberFormat.format(spending.price)

        val payersString = spending.payers.joinToString(", ")
        holder.itemView.findViewById<TextView>(R.id.tvPayers).text = payersString
        val consumersString = spending.consumers.joinToString(", ")
        holder.itemView.findViewById<TextView>(R.id.tvConsumers).text = consumersString

        holder.itemView.setOnClickListener { listener.onSpendingClicked(spending.itemId) }
    }

    override fun getItemCount(): Int {
        return spendingList.size
    }

    fun update(spendingList: List<SpendingData>) {
        this.spendingList = spendingList
        notifyDataSetChanged()
    }

    fun interface SpendingListener {

        fun onSpendingClicked(itemId: Int)
    }
}