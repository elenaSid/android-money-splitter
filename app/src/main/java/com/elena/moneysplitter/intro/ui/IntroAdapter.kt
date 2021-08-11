package com.elena.moneysplitter.intro.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * @author elena
 */
class IntroAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    private val fragments = mutableListOf<Fragment>()

    override fun getItemCount() = fragments.size

    override fun createFragment(position: Int) = fragments[position]

    fun setFragmentList(list: List<Fragment>) {
        fragments.clear()
        fragments.addAll(list)
        notifyDataSetChanged()
    }
}