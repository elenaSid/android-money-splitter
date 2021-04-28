package com.elena.moneysplitter.extras

import android.graphics.Rect
import android.view.View
import androidx.annotation.Px
import androidx.annotation.Size
import androidx.recyclerview.widget.RecyclerView

/**
 * @author elena
 */
class SpaceDecoration(
        @Px @Size(4) private val offsetArray: List<Int>
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
    ) {
        outRect.left = offsetArray[0]
        outRect.top = offsetArray[1]
        outRect.right = offsetArray[2]
        outRect.bottom = offsetArray[3]
    }
}