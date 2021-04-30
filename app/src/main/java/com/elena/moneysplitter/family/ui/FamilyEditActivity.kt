package com.elena.moneysplitter.family.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
    fun provideWFamilyEditPresenter() = presenter

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
        binding.edtFamilyName.addTextChangedListener(textWatcher)
        binding.btnSave.setOnClickListener { presenter.onFamilySaveRequested() }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.edtFamilyName.removeTextChangedListener(textWatcher)
    }

    override fun setFamilyName(familyName: String) {
        binding.edtFamilyName.setText(familyName)
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
        }
    }

    private fun initFamilyMembersList() {
        binding.rvUsers.layoutManager = TaggedLayoutManager()
        val spaces = listOf(4.toPx(), 4.toPx(), 4.toPx(), 4.toPx())
        binding.rvUsers.addItemDecoration(SpaceDecoration(spaces))
        binding.rvUsers.adapter = adapter
    }
}