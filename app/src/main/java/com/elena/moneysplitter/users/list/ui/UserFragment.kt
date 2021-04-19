package com.elena.moneysplitter.users.list.ui

import android.content.Context
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.TextView
import com.elena.domain.user.UserEntity
import com.elena.moneysplitter.R
import com.elena.moneysplitter.databinding.UsersFragmentBinding
import com.elena.moneysplitter.users.edit.ui.EditUserActivity
import com.elena.moneysplitter.users.list.mvp.UsersPresenter
import com.elena.moneysplitter.users.list.mvp.UsersView
import com.elena.moneysplitter.utils.DisplayUtils
import dagger.android.support.AndroidSupportInjection
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

/**
 * @author elena
 *         Date: 03/01/2019
 *         Time: 23:03
 */
class UserFragment : MvpAppCompatFragment(), UsersView, UserAdapter.UserListener {

    companion object {
        private const val REQUEST_EDIT = 0
        private const val REQUEST_CREATE = 1

    }
    private lateinit var binding: UsersFragmentBinding
    private lateinit var adapter: UserAdapter

    @Inject
    @InjectPresenter
    internal lateinit var presenter: UsersPresenter

    @ProvidePresenter
    fun provideUsersPresenter(): UsersPresenter {
        return presenter
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fr_users, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.fabAddUser.setOnClickListener { launchEditUserActivity(null) }
        initUsersList()
    }

    private fun initUsersList() {
        adapter = UserAdapter(this)
        binding.rvUsers.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context, androidx.recyclerview.widget.LinearLayoutManager.VERTICAL, false)
        binding.rvUsers.adapter = adapter
    }

    private fun launchEditUserActivity(user: UserEntity?) {
        val context = context
        if (context != null) {
            startActivityForResult(EditUserActivity.get(context, user),
                    if (user == null) REQUEST_CREATE else REQUEST_EDIT)
        }
    }

    override fun onMoreClicked(anchor: View, user: UserEntity) {
        val popupWindow = PopupWindow(context)
        val popupContent = View.inflate(context, R.layout.menu_view, null)
        (popupContent.findViewById(R.id.btnEdit) as TextView).setOnClickListener {
            launchEditUserActivity(user)
            popupWindow.dismiss()
        }
        (popupContent.findViewById(R.id.btnDelete) as TextView).setOnClickListener {
            presenter.onUserDeleted(user)
            popupWindow.dismiss()
        }
        popupWindow.contentView = popupContent
        popupWindow.width = ViewGroup.LayoutParams.WRAP_CONTENT
        popupWindow.height = ViewGroup.LayoutParams.WRAP_CONTENT
        popupWindow.contentView = popupContent
        popupWindow.isOutsideTouchable = true

        val anchorScreenPosition = IntArray(2)
        anchor.getLocationInWindow(anchorScreenPosition)
        val yOffset = anchorScreenPosition[1] - DisplayUtils.dpToPx(resources, 48F)
        popupWindow.showAtLocation(anchor, Gravity.END or Gravity.TOP,
                DisplayUtils.dpToPx(resources, 19F), yOffset)
    }

    override fun updateUsers(users: List<UserEntity>) {
        adapter.update(users = users)
    }
}