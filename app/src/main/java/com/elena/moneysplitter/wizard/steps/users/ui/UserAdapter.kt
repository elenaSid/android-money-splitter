package com.elena.moneysplitter.wizard.steps.users.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elena.domain.user.UserEntity
import com.elena.moneysplitter.R
import com.google.android.material.chip.Chip

/**
 * @author elena
 */
class UserAdapter(private val listener: UserListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var users = emptyList<UserEntity>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.view_tag_item, null)
        return object : RecyclerView.ViewHolder(view) {}
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val user = users[position]
        val chipTag = holder.itemView as Chip
        chipTag.text = user.name
        chipTag.setOnCloseIconClickListener { listener.onUserRemoved(user) }
    }

    fun update(users: List<UserEntity>) {
        this.users = users
        notifyDataSetChanged()
    }

    fun interface UserListener {

        fun onUserRemoved(user: UserEntity)
    }
}