package com.elena.moneysplitter.wizard.steps.summary.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.elena.domain.summary.SummaryForUser
import com.elena.moneysplitter.R
import java.text.DecimalFormat

/**
 * @author elena
 */
class SummaryForUserAdapter(
        private val summaryForUsers: List<SummaryForUser>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val numberFormat = DecimalFormat("##.###")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
                R.layout.view_summary_user_item,
                parent,
                false
        )
        return object : RecyclerView.ViewHolder(view) {}
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val summary = summaryForUsers[position]
        holder.itemView.findViewById<TextView>(R.id.tvUser).text = summary.userName
        val diff = summary.getDifference()
        holder.itemView.setBackgroundResource(
                when {
                    diff < 0 -> R.color.red_orange
                    diff == 0.0 -> android.R.color.transparent
                    else -> R.color.turquoise_blue
                }
        )
        holder.itemView.findViewById<TextView>(R.id.tvPaid).text = numberFormat.format(summary.paid)
        holder.itemView.findViewById<TextView>(R.id.tvConsume).text = numberFormat.format(summary.spent)
        holder.itemView.findViewById<TextView>(R.id.tvDifference).text = numberFormat.format(diff)
    }

    override fun getItemCount(): Int {
        return summaryForUsers.size
    }
}