package com.elena.moneysplitter.users.edit.ui

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.annotation.IntDef
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.elena.domain.family.FamilyEntity
import com.elena.moneysplitter.R

/**
 * @author elena
 *         Date: 07/01/2019
 *         Time: 10:22
 */
class FamilyAdapter(val listener: FamilyListener, val families: List<FamilyEntity>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        @IntDef(TYPE_ITEM, TYPE_CREATION)
        @Retention(AnnotationRetention.SOURCE)
        annotation class ViewType

        const val TYPE_ITEM = 0
        const val TYPE_CREATION = 1

    }

    override fun getItemViewType(position: Int): Int {
        if (position == itemCount - 1) {
            return TYPE_CREATION
        }
        return TYPE_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val context = parent.context
        val textView = (LayoutInflater.from(context).inflate(R.layout.item_family, parent, false) as TextView);
        if (viewType == TYPE_CREATION) {
            textView.setCompoundDrawables(getIcon(context), null, null, null)
            textView.setText(R.string.user_edit_add_family)
            textView.setOnClickListener { listener.onAddClicked() }
        }

        return object : RecyclerView.ViewHolder(textView) {}
    }

    private fun getIcon(context: Context): Drawable? {
        val drawable = ContextCompat.getDrawable(context, R.drawable.ic_add_item)
        if (drawable == null) {
            return drawable
        }
        val h = drawable.intrinsicHeight
        val w = drawable.intrinsicWidth
        drawable.setBounds(0, 0, w, h)
        return drawable
    }

    override fun getItemCount(): Int {
        return families.size + 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        @ViewType val viewType = getItemViewType(position)
        if (viewType == TYPE_CREATION) {
            return
        }
        val family = families.get(position)
        (holder.itemView as TextView).text = family.name
        (holder.itemView as TextView).setOnClickListener { listener.onItemClicked(family) }
    }

    interface FamilyListener {
        fun onItemClicked(family: FamilyEntity)
        fun onAddClicked()
    }
}