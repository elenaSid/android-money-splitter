package com.elena.moneysplitter.extras

import android.graphics.Point
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import kotlin.math.max

/**
 * @author elena
 */
class TaggedLayoutManager : RecyclerView.LayoutManager() {

    private var isRtl = false

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT,
                RecyclerView.LayoutParams.WRAP_CONTENT)
    }

    override fun canScrollHorizontally(): Boolean {
        return false
    }

    override fun canScrollVertically(): Boolean {
        return true
    }

    override fun isAutoMeasureEnabled(): Boolean {
        return true
    }

    override fun onLayoutChildren(recycler: Recycler, state: RecyclerView.State?) {
        isRtl = layoutDirection == View.LAYOUT_DIRECTION_RTL
        if (getVisibleAreaWidth() <= 0 && childCount != 0) {
            return
        }
        onLayoutChildren(recycler)
    }

    /**
     * Выполняет отрисовку дочерних элементов
     */
    private fun onLayoutChildren(recycler: Recycler) {
        detachAndScrapAttachedViews(recycler)
        val startPoint = getStartPoint()
        var x = startPoint.x
        var y = startPoint.y
        var height = 0
        var isNewLine: Boolean
        val rect = Rect()
        var linesCount = 1
        for (i in 0 until itemCount) {
            val child = recycler.getViewForPosition(i)
            measureChildWithMargins(child, 0, 0)
            addView(child)
            isNewLine = isCalculatedChildRectInNewLine(child, x, y, height, rect)
            linesCount = if (isNewLine) linesCount + 1 else linesCount
            layoutDecorated(child, rect.left, rect.top, rect.right, rect.bottom)
            if (isNewLine) {
                val newLinePoint = getNewLineStartPoint(rect)
                x = newLinePoint.x
                y = newLinePoint.y
                height = rect.height()
                continue
            }
            x = getEmptySpaceInLine(x, rect)
            height = max(height, rect.height())
        }
    }

    /**
     * Возвращает правую крайнюю точку в видимой области
     */
    private fun getRightVisibleEdge(): Int {
        return width - paddingRight
    }

    /**
     * Проверяет вместится ли отображаемая вью на текущую строку и
     * вычисляет координаты для [Rect] отображаемого вью, где [lineHeight] - высота строки
     */
    private fun isCalculatedChildRectInNewLine(child: View, x: Int, y: Int, lineHeight: Int,
                                               rect: Rect): Boolean {
        val childWidth = getDecoratedMeasuredWidth(child)
        val childHeight = getDecoratedMeasuredHeight(child)
        val isNewLine = isStartNewline(x, childWidth, paddingLeft, getRightVisibleEdge())
        if (isRtl) {
            rect.left = if (isNewLine) getRightVisibleEdge() - childWidth else x - childWidth
            rect.top = if (isNewLine) y + lineHeight else y
            rect.right = if (isNewLine) getRightVisibleEdge() else x
            rect.bottom = rect.top + childHeight
            return isNewLine
        }
        rect.left = if (isNewLine) paddingLeft else x
        rect.top = if (isNewLine) y + lineHeight else y
        rect.right = rect.left + childWidth
        rect.bottom = rect.top + childHeight
        return isNewLine
    }

    /**
     * Возвращает стартовую точку [Point] для начала новой "строки"
     */
    private fun getNewLineStartPoint(rect: Rect): Point {
        return if (isRtl) {
            Point(getRightVisibleEdge() - rect.width(), rect.top)
        } else {
            Point(paddingLeft + rect.width(), rect.top)
        }
    }

    /**
     * Возвращает оставшееся пространство после отрисовки [Rect] с указанной точки
     */
    private fun getEmptySpaceInLine(x: Int, rect: Rect): Int {
        return if (isRtl) x - rect.width() else x + rect.width()
    }

    /**
     * Возвращает ширину видимой области
     */
    private fun getVisibleAreaWidth(): Int {
        return width - paddingRight - paddingLeft
    }

    /**
     * Проверяет нужно ли выполнять отрисовку текущего вью с новой "строки",
     * где [leftEdge] крайняя левая точка, [rightEdge] крайняя правая точка,
     * а [childWidth] - длина отображаемого вью
     */
    private fun isStartNewline(x: Int, childWidth: Int, leftEdge: Int, rightEdge: Int): Boolean {
        return if (isRtl) x - childWidth < leftEdge else x + childWidth > rightEdge
    }

    /**
     * Возвращает начальную точку [Point] отрисовки элементов
     */
    private fun getStartPoint(): Point {
        return if (isRtl) {
            Point(width - paddingRight, paddingTop)
        } else {
            Point(paddingLeft, paddingTop)
        }
    }
}