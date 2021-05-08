package com.elena.moneysplitter.spending.ui

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
class SpendingUserAdapter(
        private val listener: UserListener? = null
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var users = emptyList<UserEntity>()
    var selectedUsers = emptyList<UserEntity>()

    enum class ViewType(val value: Int) {
        EVERYONE(0),
        OTHER(1)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.view_user_item, viewGroup, false)
        return object : RecyclerView.ViewHolder(view) {}
    }

    override fun getItemCount(): Int {
        return users.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return ViewType.EVERYONE.value
        }
        return ViewType.OTHER.value
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val userChip = holder.itemView as Chip
        val isEveryoneSelected = selectedUsers.containsAll(users)

        if (getItemViewType(position) == ViewType.EVERYONE.value) {
            userChip.text = holder.itemView.context.getString(R.string.spending_everyone)
            userChip.setOnClickListener { listener?.onUserClicked(users) }
            userChip.setChipBackgroundColorResource(
                    if (isEveryoneSelected) R.color.colorAccent else R.color.alto_bg
            )
            userChip.setTextColor(if (isEveryoneSelected) Color.WHITE else Color.BLACK)
            return
        }
        val user = users[position - 1]
        val isSelected = selectedUsers.contains(user)
        userChip.text = user.name
        userChip.setOnClickListener { listener?.onUserClicked(listOf(user)) }
        userChip.setChipBackgroundColorResource(
                if (isSelected && !isEveryoneSelected) R.color.colorAccent else R.color.alto_bg
        )
        userChip.setTextColor(if (isSelected && !isEveryoneSelected) Color.WHITE else Color.BLACK)
        userChip.isEnabled = !isEveryoneSelected
    }

    fun update(
            users: List<UserEntity> = emptyList(),
            selectedUsers: List<UserEntity>
    ) {
        this.users = users
        this.selectedUsers = selectedUsers
        notifyDataSetChanged()
    }

    fun interface UserListener {

        fun onUserClicked(user: List<UserEntity>)
    }
}