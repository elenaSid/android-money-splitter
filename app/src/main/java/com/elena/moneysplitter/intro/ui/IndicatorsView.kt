package com.elena.moneysplitter.intro.ui

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.elena.moneysplitter.R
import com.elena.moneysplitter.extras.toPx


private const val INVALID_VALUE = -1

/**
 * @author elena
 */
class IndicatorsView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    @ColorInt
    private var colorSelected = Color.WHITE

    @ColorInt
    private var colorUnselected = Color.GRAY
    private var indicatorCount = 0
    private var selectedPosition = 0

    init {
        applyAttributes(attrs, defStyleAttr)
        updateIndicators()
    }

    fun setCurrentPosition(position: Int) {
        val prevPosition = selectedPosition
        if (position in 0..indicatorCount) {
            selectedPosition = position
            for (index in 0 until indicatorCount) {
                val indicator = getChildAt(index)
                when (index) {
                    selectedPosition -> animateBackground(indicator, colorUnselected, colorSelected)
                    prevPosition -> animateBackground(indicator, colorSelected, colorUnselected)
                    else -> indicator.backgroundTintList = ColorStateList.valueOf(colorUnselected)
                }
            }
        }
    }

    private fun animateBackground(indicator: View, @ColorInt from: Int, @ColorInt to: Int) {
        val animator = ValueAnimator.ofInt(from, to)
        animator.duration = 500L
        animator.setEvaluator(ArgbEvaluator())
        animator.addUpdateListener { animation ->
            val animatedValue = animation.animatedValue as Int
            indicator.backgroundTintList = ColorStateList.valueOf(animatedValue)
        }
        animator.start()
    }

    private fun updateIndicators() {
        removeAllViews()
        gravity = Gravity.CENTER
        for (i in 0 until indicatorCount) {
            val indicator = View(context)
            val layoutParams = LayoutParams(10f.toPx(), 10f.toPx())
            layoutParams.setMargins(3f.toPx(), 3f.toPx(), 3f.toPx(), 3f.toPx())
            indicator.layoutParams = layoutParams
            indicator.setBackgroundResource(R.drawable.oval_indicator)
            indicator.backgroundTintList = ColorStateList.valueOf(colorUnselected)
            addView(indicator)
        }
    }

    private fun applyAttributes(attrs: AttributeSet?, defStyleAttr: Int) {
        val typedArray = context.obtainStyledAttributes(
                attrs, R.styleable.IndicatorsView, defStyleAttr, 0)
        try {
            indicatorCount = typedArray.getInt(R.styleable.IndicatorsView_indicatorCount, 0)
            @ColorRes val selectedColorRes = typedArray.getResourceId(
                    R.styleable.IndicatorsView_indicatorColorSelected,
                    INVALID_VALUE
            )
            if (selectedColorRes != INVALID_VALUE) {
                colorSelected = selectedColorRes.toColor(context)
            }
            @ColorRes val unselectedColorRes = typedArray.getResourceId(
                    R.styleable.IndicatorsView_indicatorColorUnselected,
                    INVALID_VALUE
            )
            if (unselectedColorRes != INVALID_VALUE) {
                colorUnselected = unselectedColorRes.toColor(context)
            }
        } finally {
            typedArray.recycle()
        }
    }

    /**
     * Выполняет преобразование ресурса цвета в цвет
     */
    @ColorInt
    private fun @receiver:ColorRes Int.toColor(context: Context) =
            ContextCompat.getColor(context, this)
}