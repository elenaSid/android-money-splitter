package com.elena.moneysplitter.users.edit

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.elena.moneysplitter.R
import com.elena.moneysplitter.databinding.UserEditActivityBinding
import com.elena.moneysplitter.users.edit.mvp.UserEditPresenter
import com.elena.moneysplitter.users.edit.mvp.UserEditView
import dagger.android.AndroidInjection
import javax.inject.Inject

/**
 * @author elena
 *         Date: 04/01/2019
 *         Time: 18:25
 */
open class EditUserActivity : MvpAppCompatActivity(), UserEditView {

    companion object {
        private const val MENU_DONE_ITEM_ID = 1
        const val PARAM_USER_NAME = "user_param_name"
        const val PARAM_USER_FAMILY = "user_param_family"

        fun get(context: Context, user: Pair<String, String>?): Intent {
            val intent = Intent(context, EditUserActivity::class.java)
            if (user != null) {
                intent.putExtra(PARAM_USER_NAME, user.first)
                intent.putExtra(PARAM_USER_FAMILY, user.second)
            }
            return intent
        }
    }

    @Inject
    @InjectPresenter
    internal lateinit var presenter: UserEditPresenter

    @ProvidePresenter
    fun provideUserEditPresenter(): UserEditPresenter {
        return presenter
    }

    private lateinit var binding:UserEditActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.ac_edit_user)
        setActionBar()

        if (intent != null) {
            val bundle = intent.extras
            if (bundle != null) {
                presenter.onGetUser(bundle.getString(PARAM_USER_NAME), bundle.getString(PARAM_USER_FAMILY))
            }

        }
    }

    private fun setActionBar() {
        setSupportActionBar(binding.toolbar)
        val title = getString(if (intent.extras != null) R.string.user_edit_edit else R.string.user_edit_create)
        this.supportActionBar?.title = title
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (menu == null) {
            return super.onCreateOptionsMenu(menu)
        }
        val item = menu.add(Menu.NONE, MENU_DONE_ITEM_ID, Menu.NONE, R.string.user_edit_menu_item_save)
        item.setIcon(R.drawable.ic_done_gray)
        item.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item == null) {
            return super.onOptionsItemSelected(item)
        }
        if (item.itemId == MENU_DONE_ITEM_ID) {
            val intent = Intent()
            //TODO:передать сохраненного пользователя
            setResult(Activity.RESULT_OK, intent)
        }
        finish()
        return true
    }

    override fun setName(name: String) {
        binding.edtName.setText(name)
    }

    override fun setFamily(family: String) {
        binding.edtFamily.setText(family)
    }
}