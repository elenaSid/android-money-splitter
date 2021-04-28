package com.elena.moneysplitter.root.ui

import androidx.databinding.DataBindingUtil
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.view.MenuItem
import com.elena.moneysplitter.R
import com.elena.moneysplitter.databinding.RootActivityBinding
import com.elena.moneysplitter.items.list.ui.ItemFragment
import com.elena.moneysplitter.root.mvp.RootPresenter
import com.elena.moneysplitter.root.mvp.RootView
import com.elena.moneysplitter.users.list.ui.UserOldFragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

/**
 * @author elena
 *         Date: 12.06.2018
 *         Time: 17:17
 */
open class RootActivity : MvpAppCompatActivity(), RootView,
        BottomNavigationView.OnNavigationItemSelectedListener, HasAndroidInjector {

    companion object {
        private const val TAG = "current_fragment_tag"
    }

    private lateinit var binding: RootActivityBinding

    @Inject
    internal lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    @InjectPresenter
    internal lateinit var presenter: RootPresenter

    @ProvidePresenter
    fun provideSplitterPresenter(): RootPresenter {
        return presenter
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.ac_root)
        binding.bnvNavigation.setOnNavigationItemSelectedListener(this)
        binding.bnvNavigation.selectedItemId = R.id.menu_item_users
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        val fragment: androidx.fragment.app.Fragment
        when (menuItem.itemId) {
            in listOf(
                    R.id.menu_item_users,
                    R.id.menu_item_summary,
                    R.id.menu_item_settings) ->
                fragment = UserOldFragment()
            R.id.menu_item_shopping -> fragment = ItemFragment()
            else -> fragment = UserOldFragment()
        }
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.flContainer, fragment, TAG)
                .commitAllowingStateLoss()
        return true
    }

    override fun showEmptyView() {
    }

}