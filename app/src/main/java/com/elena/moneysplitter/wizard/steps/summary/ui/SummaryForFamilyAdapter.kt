package com.elena.moneysplitter.wizard.steps.summary.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
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
    private val expandedMap = mutableMapOf<Int, Boolean>()

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
            val isExpanded = expandedMap.getOrDefault(summary.familyId!!, false)
            holder.itemView.findViewById<TextView>(R.id.tvFamily).setCompoundDrawablesWithIntrinsicBounds(
                    if (isExpanded) R.drawable.ic_expand_less else R.drawable.ic_expand_more,
                    0,
                    0,
                    0
            )
            val rvUsers = holder.itemView.findViewById<RecyclerView>(R.id.rvUsers)
            rvUsers.adapter = SummaryForUserAdapter(summary.summaryForUsers)
            rvUsers.visibility = if (isExpanded) View.VISIBLE else View.GONE
            holder.itemView.setOnClickListener { manageExpandable(position, summary) }
        } else {
            holder.itemView.findViewById<TextView>(R.id.tvUser).text = summary.familyName
        }
        val diff = summary.getDifference()
        val layout = if (isFamily) {
            holder.itemView.findViewById<ConstraintLayout>(R.id.clFamily)
        } else {
            holder.itemView
        }
        layout.setBackgroundResource(
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
        summarySet.forEach {
            if (it.familyId != null) {
                expandedMap[it.familyId!!] = false
            }
        }
        notifyDataSetChanged()
    }

    private fun manageExpandable(position: Int, summary: SummaryForFamily) {
        val isExpanded = expandedMap[summary.familyId]
        isExpanded?.let {
            expandedMap.replace(summary.familyId!!, !it)
        }
        notifyItemChanged(position)
    }
}