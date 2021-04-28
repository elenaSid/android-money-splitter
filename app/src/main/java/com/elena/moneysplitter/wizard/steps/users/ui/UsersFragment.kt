package com.elena.moneysplitter.wizard.steps.users.ui

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.elena.domain.user.UserEntity
import com.elena.moneysplitter.R
import com.elena.moneysplitter.databinding.UsersWizardFragmentBinding
import com.elena.moneysplitter.extras.SpaceDecoration
import com.elena.moneysplitter.extras.TaggedLayoutManager
import com.elena.moneysplitter.extras.toPx
import com.elena.moneysplitter.extras.trimmedContent
import com.elena.moneysplitter.wizard.steps.users.mvp.UsersMvpView
import com.elena.moneysplitter.wizard.steps.users.mvp.UsersPresenter
import dagger.android.support.AndroidSupportInjection
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

/**
 * @author elena
 */
class UsersFragment : MvpAppCompatFragment(), UsersMvpView {

    @Inject
    @InjectPresenter
    lateinit var presenter: UsersPresenter
    private lateinit var binding: UsersWizardFragmentBinding
    private val adapter = UserAdapter { presenter.onUserDeleted(it) }
    private val textWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable) {
            manageAddBtnAvailability(s.trimmedContent().isNotEmpty())
        }
    }

    @ProvidePresenter
    fun provideUsersPresenter() = presenter

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fr_wizard_users, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUserList()
        binding.edtUserName.addTextChangedListener(textWatcher)
        manageAddBtnAvailability(false)
        binding.btnAddUser.setOnClickListener {
            binding.edtUserName.text?.trimmedContent()?.let { name -> presenter.onUserCreated(name) }
            binding.edtUserName.text = null
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.edtUserName.removeTextChangedListener(textWatcher)
    }

    override fun updateUserList(users: List<UserEntity>) {
        adapter.update(users)
    }

    private fun initUserList() {
        binding.rvUsers.layoutManager = TaggedLayoutManager()
        val spaces = listOf(4.toPx(), 4.toPx(), 4.toPx(), 4.toPx())
        binding.rvUsers.addItemDecoration(SpaceDecoration(spaces))
        binding.rvUsers.adapter = adapter
    }

    private fun manageAddBtnAvailability(isEnabled: Boolean) {
        binding.btnAddUser.isEnabled = isEnabled
        binding.btnAddUser.alpha = if (isEnabled) 1f else 0.5f
    }
}