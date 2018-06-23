package com.elena.moneysplitter.splitter.ui

import android.content.Context
import android.databinding.DataBindingUtil
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.elena.moneysplitter.R
import com.elena.moneysplitter.databinding.FloatingActionButtonViewBinding
import kotlinx.android.synthetic.main.view_fab_menu.view.*

/**
 * @author elena
 * Date: 23.06.2018
 * Time: 10:48
 */
class FloatingActionButtonMenu : FrameLayout {

    private lateinit var binding: FloatingActionButtonViewBinding
    private var listener: MenuListener? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        init()
    }

    private fun init() {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.view_fab_menu, this, true)
        binding.fabPerson.setOnClickListener {
            if (listener != null) {
                listener!!.onPersonClicked()
                hideMenu()
            }
        }
        binding.fabItem.setOnClickListener {
            if (listener != null) {
                listener!!.onItemClicked()
                hideMenu()
            }
        }
        binding.fabMenu.setOnClickListener {
            if (isMenuShown()) {
                hideMenu()
            } else {
                showMenu()
            }
        }
    }

    /**
     * Set listener for events of FAB menu
     */
    fun setListener(listener: MenuListener) {
        this.listener = listener
    }

    /**
     * Show menu items
     */
    private fun showMenu() {
        manageMenuVisibility(true)
        binding.llBackground.setOnClickListener { hideMenu() }
    }

    fun isMenuShown(): Boolean {
        return binding.llItemMenu.visibility == View.VISIBLE || binding.llPersonMenu.visibility == View.VISIBLE
    }

    /**
     *
     */
    fun hideMenu() {
        manageMenuVisibility(false)
        binding.llBackground.setOnClickListener(null)
    }

    /**
     * Manage the visibility of FAB menu items to user
     *
     * @param isVisible true if it is visible to user, false - invisible
     */
    private fun manageMenuVisibility(isVisible: Boolean) {
        llPersonMenu.visibility = if (isVisible) View.VISIBLE else View.GONE
        llItemMenu.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    interface MenuListener {
        /**
         * "Person" menu item selected
         */
        fun onPersonClicked()

        /**
         * "Item" menu item selected
         */
        fun onItemClicked()
    }
}
