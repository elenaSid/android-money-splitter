package com.elena.moneysplitter.wizard.steps.families.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elena.domain.user.UserEntity
import com.elena.moneysplitter.R
import com.google.android.material.chip.Chip

/**
 * @author elena
 */
class FamilyMembersAdapter(
        private val isEditable: Boolean = false,
        private val listener: UserListener? = null
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var users = emptyList<UserEntity>()
    private var usersInFamily = emptyList<UserEntity>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.view_user_item, viewGroup, false)
        return object : RecyclerView.ViewHolder(view) {}
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val user = users[position]
        val familyMemberChip = holder.itemView as Chip

        val isSelected = usersInFamily.contains(user)
        familyMemberChip.text = user.name
        familyMemberChip.setOnClickListener { listener?.onUserClicked(user) }
        familyMemberChip.isClickable = isEditable
        familyMemberChip.isFocusable = isEditable
        familyMemberChip.setChipBackgroundColorResource(
                if (isSelected) R.color.colorAccent else R.color.alto_bg
        )
        familyMemberChip.setTextColor(if (isSelected) Color.WHITE else Color.BLACK)
    }

    fun update(
            users: List<UserEntity> = emptyList(),
            usersInFamily: List<UserEntity>
    ) {
        this.users = if (!isEditable) usersInFamily else users
        this.usersInFamily = usersInFamily
        notifyDataSetChanged()
    }

    fun interface UserListener {

        fun onUserClicked(user: UserEntity)
    }
}