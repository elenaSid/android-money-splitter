package com.elena.moneysplitter.root.ui

import androidx.databinding.DataBindingUtil
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.fragment.app.Fragment
import android.view.MenuItem
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.elena.moneysplitter.R
import com.elena.moneysplitter.databinding.RootActivityBinding
import com.elena.moneysplitter.items.list.ui.ItemFragment
import com.elena.moneysplitter.root.mvp.RootPresenter
import com.elena.moneysplitter.root.mvp.RootView
import com.elena.moneysplitter.users.list.ui.UserFragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * @author elena
 *         Date: 12.06.2018
 *         Time: 17:17
 */
open class RootActivity : MvpAppCompatActivity(), RootView,
        BottomNavigationView.OnNavigationItemSelectedListener, HasSupportFragmentInjector {

    companion object {
        private const val TAG = "current_fragment_tag"
    }

    private lateinit var binding: RootActivityBinding

    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<androidx.fragment.app.Fragment>

    @Inject
    @InjectPresenter
    internal lateinit var presenter: RootPresenter

    @ProvidePresenter
    fun provideSplitterPresenter(): RootPresenter {
        return presenter
    }

    override fun supportFragmentInjector(): AndroidInjector<androidx.fragment.app.Fragment> {
        return fragmentDispatchingAndroidInjector
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
                fragment = UserFragment()
            R.id.menu_item_shopping -> fragment = ItemFragment()
            else -> fragment = UserFragment()
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