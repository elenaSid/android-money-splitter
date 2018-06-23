package com.elena.moneysplitter.splitter.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.elena.moneysplitter.R
import com.elena.moneysplitter.databinding.SplitterActivityBinding
import com.elena.moneysplitter.splitter.presenter.SplitterPresenter
import com.elena.moneysplitter.splitter.view.SplitterView
import dagger.android.AndroidInjection
import javax.inject.Inject

/**
 * @author elena
 *         Date: 12.06.2018
 *         Time: 17:17
 */
open class SplitterActivity : MvpAppCompatActivity(), SplitterView, FloatingActionButtonMenu.MenuListener {

    private lateinit var binding: SplitterActivityBinding

    @Inject
    @InjectPresenter
    internal lateinit var presenter: SplitterPresenter

    @ProvidePresenter
    fun provideSplitterPresenter(): SplitterPresenter {
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.ac_splitter)
        binding.fabMenu.setListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_splitter, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == R.id.menuSettings) {
            //TODO: launch settings activity
            Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (binding.fabMenu.isMenuShown()) {
            binding.fabMenu.hideMenu()
        } else {
            super.onBackPressed()
        }

    }

    override fun onPersonClicked() {
        //TODO: launch creating a new person
        Toast.makeText(this, "Add a person", Toast.LENGTH_SHORT).show()
    }

    override fun onItemClicked() {
        //TODO: launch creating a new item
        Toast.makeText(this, "Add an item", Toast.LENGTH_SHORT).show()
    }

    override fun showEmptyView() {
    }

}