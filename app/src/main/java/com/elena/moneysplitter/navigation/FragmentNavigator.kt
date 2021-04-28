package com.elena.moneysplitter.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import com.github.terrakok.cicerone.androidx.AppNavigator

/**
 * @author elena
 */
class FragmentNavigator(
        activity: FragmentActivity,
        containerId: Int
) : AppNavigator(activity, containerId) {

    override fun setupFragmentTransaction(
            fragmentTransaction: FragmentTransaction,
            currentFragment: Fragment?,
            nextFragment: Fragment?
    ) {
        fragmentTransaction.setCustomAnimations(
                android.R.anim.fade_in,
                android.R.anim.fade_out,
                android.R.anim.fade_in,
                android.R.anim.fade_out
        )
    }
}