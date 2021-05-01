package com.elena.moneysplitter.wizard.steps.families.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.elena.domain.family.FamilyEntity
import com.elena.domain.family.FamilyMembers
import com.elena.moneysplitter.R
import com.elena.moneysplitter.extras.SpaceDecoration
import com.elena.moneysplitter.extras.TaggedLayoutManager
import com.elena.moneysplitter.extras.toPx

/**
 * @author elena
 */
class FamilyAdapter(private val listener: FamilyListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var families = emptyList<FamilyMembers>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_family_item, parent, false)
        val rvFamilyMembers = view.findViewById<RecyclerView>(R.id.rvFamilyMembers)
        rvFamilyMembers.layoutManager = TaggedLayoutManager()
        val spaces = listOf(4.toPx(), 4.toPx(), 4.toPx(), 4.toPx())
        rvFamilyMembers.addItemDecoration(SpaceDecoration(spaces))
        return object : RecyclerView.ViewHolder(view) {}
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val family = families[position]
        holder.itemView.findViewById<TextView>(R.id.tvFamily).text = family.family.name
        val rvFamilyMembers = holder.itemView.findViewById<RecyclerView>(R.id.rvFamilyMembers)
        val adapter = FamilyMembersAdapter()
        rvFamilyMembers.adapter = adapter
        adapter.update(usersInFamily = family.users)
        holder.itemView.setOnClickListener { listener.onFamilyClicked(family.family) }
    }

    override fun getItemCount(): Int = families.size

    fun update(families: List<FamilyMembers>) {
        this.families = families
        notifyDataSetChanged()
    }

    fun interface FamilyListener {
        fun onFamilyClicked(family: FamilyEntity)
    }
}