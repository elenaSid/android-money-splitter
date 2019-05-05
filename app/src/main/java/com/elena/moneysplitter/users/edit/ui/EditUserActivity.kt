package com.elena.moneysplitter.users.edit.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.elena.domain.family.FamilyEntity
import com.elena.domain.user.UserEntity
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
        private const val REQUEST_ADD_FAMILY = 1
        const val PARAM_USER_ID = "user_param_id"

        fun get(context: Context, user: UserEntity?): Intent {
            val intent = Intent(context, EditUserActivity::class.java)
            if (user != null) {
                intent.putExtra(PARAM_USER_ID, user.id)
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
                presenter.onGetUser(bundle.getInt(PARAM_USER_ID))
            }
        }
        initWidgets()
    }

    private fun setActionBar() {
        setSupportActionBar(binding.toolbar)
        val title = getString(if (intent.extras != null) R.string.user_edit_edit else R.string.user_edit_create)
        this.supportActionBar?.title = title
    }

    private fun initWidgets() {
        binding.edtFamily.setOnClickListener { presenter.onFamilyListClicked() }
        binding.edtFamily.setOnFocusChangeListener { _, _ ->  presenter.onFamilyListClicked()}
        binding.edtFamily.showSoftInputOnFocus = false
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_ADD_FAMILY &&
                data != null) {
            val bundle = data.extras ?: return
            val family = bundle.getString(AddFamilyActivity.PARAM_FAMILY) ?: return
            Toast.makeText(this, family, Toast.LENGTH_SHORT).show()
            presenter.onFamilyAdded(family)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (menu == null) {
            return super.onCreateOptionsMenu(menu)
        }
        val item = menu.add(Menu.NONE, R.id.menu_item_done, Menu.NONE, R.string.user_edit_menu_item_save)
        item.setIcon(R.drawable.ic_done_gray)
        item.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item == null) {
            return super.onOptionsItemSelected(item)
        }
        if (item.itemId == R.id.menu_item_done) {
            presenter.onUserSave(binding.edtName.text.toString().trim(), getFamily())
            return true
        }
        finish()
        return true
    }

    override fun setName(name: String) {
        binding.edtName.setText(name)
    }


    private fun getFamily(): FamilyEntity = binding.edtFamily.tag as FamilyEntity

    override fun showFamilies(families: List<FamilyEntity>) {
        manageListArrow(true)
        val menu = UserDropdownMenu(this, families,
                { family: FamilyEntity -> setFamily(family) },
                { launchAddFamilyActivity() })
        menu.height = WindowManager.LayoutParams.WRAP_CONTENT
        menu.width = binding.edtFamily.width
        menu.isOutsideTouchable = true
        menu.isFocusable = true
        menu.showAsDropDown(binding.edtFamily)
        menu.setOnDismissListener { manageListArrow(false) }
    }

    override fun finishWithOkResult() {
        setResult(Activity.RESULT_OK)
        finish()
    }

    override fun setFamily(family: FamilyEntity) {
        binding.edtFamily.setText(family.name)
        binding.edtFamily.tag = family
    }

    private fun manageListArrow(isOpen: Boolean) {
        binding.edtFamily.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null,
                if (isOpen) ContextCompat.getDrawable(this, R.drawable.ic_dropup)
                else ContextCompat.getDrawable(this, R.drawable.ic_dropdown), null)
    }

    private fun launchAddFamilyActivity() {
        startActivityForResult(Intent(this, AddFamilyActivity::class.java), REQUEST_ADD_FAMILY)
    }
}