package com.elena.moneysplitter.items.list.ui

import android.support.annotation.IntDef
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.elena.moneysplitter.R

/**
 * @author elena
 *         Date: 08/01/2019
 *         Time: 15:48
 */
class ItemAdapter(private val listener: ItemListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        @IntDef(TYPE_HEADER, TYPE_ITEM)
        @Retention(AnnotationRetention.SOURCE)
        annotation class ViewType

        const val TYPE_HEADER = 0
        const val TYPE_ITEM = 1

    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return ItemAdapter.TYPE_HEADER
        }
        return ItemAdapter.TYPE_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
                if (viewType == TYPE_HEADER) R.layout.item_header_items else R.layout.item_shopping_item, null)
        view.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT)
        return object : RecyclerView.ViewHolder(view) {}
    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.setOnClickListener { listener.onItemClicked() }
    }


    interface ItemListener {

        //TODO: Передавать item
        fun onMoreClicked(anchor: View)

        fun onItemClicked()
    }
}