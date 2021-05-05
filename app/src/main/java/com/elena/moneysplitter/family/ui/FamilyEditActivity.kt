package com.elena.moneysplitter.family.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.databinding.DataBindingUtil
import com.elena.domain.user.UserEntity
import com.elena.moneysplitter.R
import com.elena.moneysplitter.databinding.FamilyEditFragmentBinding
import com.elena.moneysplitter.extras.SpaceDecoration
import com.elena.moneysplitter.extras.TaggedLayoutManager
import com.elena.moneysplitter.extras.toPx
import com.elena.moneysplitter.extras.trimmedContent
import com.elena.moneysplitter.family.mvp.FamilyEditMvpView
import com.elena.moneysplitter.family.mvp.FamilyEditPresenter
import com.elena.moneysplitter.wizard.steps.families.ui.FamilyMembersAdapter
import dagger.android.AndroidInjection
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

/**
 * @author elena
 */
class FamilyEditActivity : MvpAppCompatActivity(), FamilyEditMvpView {

    @Inject
    @InjectPresenter
    lateinit var presenter: FamilyEditPresenter
    lateinit var binding: FamilyEditFragmentBinding

    @ProvidePresenter
    fun provideFamilyEditPresenter() = presenter

    private val adapter = FamilyMembersAdapter(true) { presenter.onUserSelected(it) }
    private val textWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable) {
            presenter.onFamilyNameChanged(s.trimmedContent())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.ac_edit_family)
        setToolbar()
        initFamilyMembersList()
        binding.btnSave.setOnClickListener { presenter.onFamilySaveRequested() }
        parseFamilyId()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (intent != null && intent.hasExtra(PARAM_FAMILY_ID)) {
            menu?.let {
                val item = menu.add(Menu.NONE, MENU_DELETE_ID, Menu.NONE, R.string.family_delete)
                item.setIcon(R.drawable.ic_delete)
                item.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS)
            }
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == MENU_DELETE_ID) {
            presenter.onFamilyDeleteRequested()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.edtFamilyName.removeTextChangedListener(textWatcher)
    }

    override fun showEmptyState() {
        binding.tvTitle.visibility = View.GONE
        binding.rvUsers.visibility = View.GONE
        binding.tvWarning.visibility = View.VISIBLE
    }

    override fun setFamilyName(familyName: String?) {
        binding.edtFamilyName.setText(familyName)
        if (familyName == null) {
            binding.edtFamilyName.requestFocus()
        }
        binding.edtFamilyName.addTextChangedListener(textWatcher)
    }

    override fun updateFamilyMembers(users: List<UserEntity>, usersInFamily: List<UserEntity>) {
        adapter.update(users, usersInFamily)
    }

    override fun manageSaveBtnAvailability(isEnabled: Boolean) {
        binding.btnSave.isEnabled = isEnabled
    }

    override fun manageErrorMessage(hasNoError: Boolean) {
        binding.tilFamilyName.error = if (hasNoError) {
            null
        } else {
            getString(R.string.family_not_set_error)
        }
    }

    override fun saveFinish() {
        finish()
    }

    private fun setToolbar() {
        supportActionBar?.let {
            it.elevation = 0f
            it.setHomeButtonEnabled(true)
            it.setTitle(
                    if (intent.hasExtra(PARAM_FAMILY_ID)) {
                        R.string.family_edit_title
                    } else {
                        R.string.family_add_title
                    }
            )
        }
    }

    private fun initFamilyMembersList() {
        binding.rvUsers.layoutManager = TaggedLayoutManager()
        val spaces = listOf(4.toPx(), 4.toPx(), 4.toPx(), 4.toPx())
        binding.rvUsers.addItemDecoration(SpaceDecoration(spaces))
        binding.rvUsers.adapter = adapter
    }

    private fun parseFamilyId() {
        intent?.let {
            if (intent.hasExtra(PARAM_FAMILY_ID)) {
                presenter.onFamilyIdParsed(intent.getIntExtra(PARAM_FAMILY_ID, 0))
            }
        }
    }

    companion object {
        private const val MENU_DELETE_ID = 1
        private const val PARAM_FAMILY_ID = "family_id"

        fun getInstance(context: Context, familyId: Int): Intent {
            val intent = Intent(context, FamilyEditActivity::class.java)
            intent.putExtra(PARAM_FAMILY_ID, familyId)
            return intent
        }
    }
}