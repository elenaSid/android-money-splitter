package com.elena.moneysplitter.root.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.view.MenuItem
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.elena.moneysplitter.R
import com.elena.moneysplitter.databinding.RootActivityBinding
import com.elena.moneysplitter.root.presenter.RootPresenter
import com.elena.moneysplitter.root.view.RootView
import com.elena.moneysplitter.users.UsersFragment
import dagger.android.AndroidInjection
import javax.inject.Inject

/**
 * @author elena
 *         Date: 12.06.2018
 *         Time: 17:17
 */
open class RootActivity : MvpAppCompatActivity(), RootView, BottomNavigationView.OnNavigationItemSelectedListener {

    companion object {
        private const val TAG = "fragment"
    }

    private lateinit var binding: RootActivityBinding

    @Inject
    @InjectPresenter
    internal lateinit var presenter: RootPresenter

    @ProvidePresenter
    fun provideSplitterPresenter(): RootPresenter {
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.ac_root)
        binding.bnvNavigation.setOnNavigationItemSelectedListener(this)
        binding.bnvNavigation.selectedItemId = R.id.menu_item_users
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        val fragment: Fragment
        when (menuItem.itemId) {
            in listOf(
                    R.id.menu_item_users,
                    R.id.menu_item_shopping,
                    R.id.menu_item_summary,
                    R.id.menu_item_settings) ->
                fragment = UsersFragment()
            else -> fragment = UsersFragment()
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