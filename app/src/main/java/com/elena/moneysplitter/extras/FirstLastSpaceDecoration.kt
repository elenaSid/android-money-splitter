package com.elena.moneysplitter.extras

import android.graphics.Rect
import android.view.View
import androidx.annotation.Px
import androidx.annotation.Size
import androidx.recyclerview.widget.RecyclerView

/**
 * Декоратор: добавляет отступы крайним границам первого и последнего элементов списка
 *
 * @author elena
 */
class FirstLastSpaceDecoration(
    @Px @Size(4) private val offsetArray: List<Int>
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val adapter = parent.adapter ?: return
        val childPosition = parent.getChildAdapterPosition(view)
        if (offsetArray[0] != 0 || offsetArray[2] != 0) {
            addHorizontalOffset(outRect, adapter, childPosition)
        } else if (offsetArray[1] != 0 || offsetArray[3] != 0) {
            addVerticalOffset(outRect, adapter, childPosition)
        }
    }

    /**
     * Добавляет отступы из [outRect] первому и последнему элементу по горизонтали
     */
    private fun addHorizontalOffset(
        outRect: Rect,
        adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>,
        childPosition: Int
    ) {
        if (childPosition == 0) {
            outRect.left = offsetArray[0]
        } else if (childPosition == adapter.itemCount - 1) {
            outRect.right = offsetArray[2]
        }
    }

    /**
     * Добавляет отступы из [outRect] первому и последнему элементу по вертикали
     */
    private fun addVerticalOffset(
        outRect: Rect,
        adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>,
        childPosition: Int
    ) {
        if (childPosition == 0) {
            outRect.top = offsetArray[1]
        } else if (childPosition == adapter.itemCount - 1) {
            outRect.bottom = offsetArray[3]
        }
    }
}