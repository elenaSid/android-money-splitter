package com.elena.moneysplitter.wizard.steps.summary.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.elena.domain.summary.SummaryForFamily
import com.elena.moneysplitter.R
import java.text.DecimalFormat

/**
 * @author elena
 */
class SummaryForFamilyAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val numberFormat = DecimalFormat("##.###")
    private var summarySet = emptySet<SummaryForFamily>()

    enum class ViewType {
        FAMILY,
        USER
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val isFamily = viewType == ViewType.FAMILY.ordinal
        val view = LayoutInflater.from(parent.context).inflate(
                if (isFamily) {
                    R.layout.view_summary_family_item
                } else {
                    R.layout.view_summary_user_item
                },
                parent,
                false
        )

        if (isFamily) {
            val rvUsers = view.findViewById<RecyclerView>(R.id.rvUsers)
            rvUsers.layoutManager = LinearLayoutManager(
                    parent.context,
                    LinearLayoutManager.VERTICAL,
                    false
            )
        }
        return object : RecyclerView.ViewHolder(view) {}
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val isFamily = getItemViewType(position) == ViewType.FAMILY.ordinal
        val summary = summarySet.elementAt(position)

        if (isFamily) {
            holder.itemView.findViewById<TextView>(R.id.tvFamily).text = summary.familyName
        } else {
            holder.itemView.findViewById<TextView>(R.id.tvUser).text = summary.familyName
        }
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
        return summarySet.size
    }

    override fun getItemViewType(position: Int): Int {
        val summary = summarySet.elementAt(position)
        return if (summary.familyId == null) ViewType.USER.ordinal else ViewType.FAMILY.ordinal
    }

    fun update(summarySet: Set<SummaryForFamily>) {
        this.summarySet = summarySet
        notifyDataSetChanged()
    }
}