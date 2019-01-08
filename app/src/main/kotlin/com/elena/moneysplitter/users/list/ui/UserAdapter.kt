package com.elena.moneysplitter.users.list.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.elena.moneysplitter.R

/**
 * @author elena
 *         Date: 04/01/2019
 *         Time: 13:59
 */
class UserAdapter(private val listener: UserListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var users = ArrayList<Pair<String, String>>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.user_item, null)
        return object : RecyclerView.ViewHolder(view) {}
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val user = users[position]
        (holder.itemView.findViewById(R.id.tvUserName) as TextView).text = user.first
        (holder.itemView.findViewById(R.id.tvFamily) as TextView).text = user.second
        (holder.itemView.findViewById(R.id.ibMore) as ImageButton).setOnClickListener{ listener.onMoreClicked(holder.itemView, user) }
    }

    interface UserListener {

        fun onMoreClicked(anchor: View, user: Pair<String, String>)
    }
}