package com.elena.moneysplitter.navigation

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder

/**
 * @author elena
 */
class NavigationLifecycleObserver(
        private val navigator: Navigator,
        private val navigatorHolder: NavigatorHolder
) : DefaultLifecycleObserver {

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause(owner: LifecycleOwner) {
        navigatorHolder.removeNavigator()
        super.onPause(owner)
    }
}